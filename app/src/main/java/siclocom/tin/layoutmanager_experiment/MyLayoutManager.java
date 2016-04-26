package siclocom.tin.layoutmanager_experiment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by tin on 4/5/16.
 */
public class MyLayoutManager extends RecyclerView.LayoutManager {

    private int mItem = 0;
    private int mItemOffset;

    private static final int ITEM_HEIGHT = 96;
    private int mDy;

    public MyLayoutManager() {
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(MyLayoutManager.class.getSimpleName(), "onLayoutChildren: ");

        final int itemsNeededToLayout = getHeight() / ITEM_HEIGHT + 10;

        fill(recycler, state, itemsNeededToLayout);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state,
                      int itemsNeededToLayout) {

        detachAndScrapAttachedViews(recycler);
        Log.d(MyLayoutManager.class.getSimpleName(), "Layout items: " + itemsNeededToLayout);
//        Log.d(MyLayoutManager.class.getSimpleName(), "item count: " + getChildCount());
        final int startItem = mItem < 5 ? 0 : mItem - 5;
        if (state.getItemCount() > 0) {
            int end = itemsNeededToLayout + startItem;
            if (end > state.getItemCount() - mItem){
                end = state.getItemCount();
            }
            for (int i = startItem - mItem; i < end - mItem; i++) {
                View view = recycler.getViewForPosition(i + mItem);
                measureChild(view, 0, 0);
                final int height = ITEM_HEIGHT;
                final int width = getDecoratedMeasuredWidth(view);
                addView(view);
                final int left = 200;
                final int top = ( i ) * height + mItemOffset;
                final int bottom = top + height;
                layoutDecorated(view, left, top, width, bottom);
            }

            List<View> viewListToRemove = new ArrayList<>();
            final int scrapListSize = recycler.getScrapList().size();
            for (int i = 0; i < scrapListSize; i++) {
                View removingView = recycler.getScrapList().get(i).itemView;
                viewListToRemove.add(removingView);
            }

            for (int i = 0; i < viewListToRemove.size(); i++) {
                removeAndRecycleView(viewListToRemove.get(i), recycler);
            }
        }
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void scrollToPosition(int position) {
        //mScrollToItem = position;
        //requestLayout();
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.d(MyLayoutManager.class.getSimpleName(), "Dy: " + dy);
        int deltaY = dy;
        final int maximumVirtualHeight = state.getItemCount() * ITEM_HEIGHT - getHeight();
        if (mDy + dy > maximumVirtualHeight){
            deltaY = maximumVirtualHeight - mDy;
            mDy = maximumVirtualHeight;
        } else if (mDy + dy < 0){
            deltaY = -mDy;
            mDy = 0;
        } else {
            mDy += deltaY;
        }
        mItem = mDy / ITEM_HEIGHT;
        mItemOffset = - (mDy % ITEM_HEIGHT);
        offsetChildrenVertical(-deltaY);
        fill(recycler, state, getHeight() / ITEM_HEIGHT + 10);
        return deltaY;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public void layoutDecorated(View child, int left, int top, int right, int bottom) {
//        Log.d(MyLayoutManager.class.getSimpleName(), " Layout Child: " + child.toString() + " top: " + top );
        super.layoutDecorated(child, left, top, right, bottom);
    }
}
