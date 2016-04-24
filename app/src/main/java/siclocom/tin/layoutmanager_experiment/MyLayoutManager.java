package siclocom.tin.layoutmanager_experiment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.logging.Logger;

/**
 * Created by tin on 4/5/16.
 */
public class MyLayoutManager extends RecyclerView.LayoutManager {

  private int mItem = 5;
  private int mItemOffset;

  private static final int ITEM_HEIGHT = 48 * 2;
  private int mDy;

  public MyLayoutManager() {
  }

  @Override public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
    Log.d(MyLayoutManager.class.getSimpleName(), "onLayoutChildren: ");

    final int itemsNeededToLayout = getHeight() / ITEM_HEIGHT;

    fill(recycler, state, itemsNeededToLayout);
  }

  private void fill(RecyclerView.Recycler recycler, RecyclerView.State state,
      int itemsNeededToLayout) {
    detachAndScrapAttachedViews(recycler);

    if (state.getItemCount() > 0) {
      for (int i = 0; i < itemsNeededToLayout; i++) {
          View view = recycler.getViewForPosition(mItem + i);
        measureChild(view, 0, 0);
        final int height = ITEM_HEIGHT;
        final int width = getDecoratedMeasuredWidth(view);
        addView(view);
        layoutDecorated(view, 0, i * ITEM_HEIGHT, width, i * ITEM_HEIGHT + height);
      }
    }
  }

  @Override public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
  }

  @Override public void scrollToPosition(int position) {
    //mScrollToItem = position;
    //requestLayout();
  }

  @Override
  public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
    Log.d(MyLayoutManager.class.getSimpleName(), "Dy: +" + dy);
    mDy += dy;
    mItem += mDy / 96;
    mItemOffset += mDy % 96;
    fill(recycler, state, getHeight() / ITEM_HEIGHT);
    return dy;
  }

  @Override public boolean canScrollVertically() {
    return true;
  }

  @Override public void layoutDecorated(View child, int left, int top, int right, int bottom) {
    Log.d(MyLayoutManager.class.getSimpleName(), " Layout Child: " + child.toString() + " left: " + left + " top: " + top + " right: " + right + " bottom: " + bottom);
    super.layoutDecorated(child, left, top, right, bottom);
  }
}
