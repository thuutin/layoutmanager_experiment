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

  public MyLayoutManager() {
  }

  @Override public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
    Log.d(MyLayoutManager.class.getSimpleName(), "onLayoutChildren: " );
    detachAndScrapAttachedViews(recycler);
    if (state.getItemCount() > 0) {
     final int count = state.getItemCount();
     for (int i = 0; i < count; i++) {
        View view = recycler.getViewForPosition(i);
       measureChild(view ,0, 0);
       final int height = getDecoratedMeasuredHeight(view);
       final int width = getDecoratedMeasuredWidth(view);
       addView(view);
       layoutDecorated(view, 0, i * 48, width, i * 48 + height);
     }
   }
  }

  @Override public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return new RecyclerView.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    );
  }

  @Override public void scrollToPosition(int position) {
    //mScrollToItem = position;
    //requestLayout();
  }

  @Override
  public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
    Log.d(MyLayoutManager.class.getSimpleName(), "Dy: +" + dy);
    mItem = dy / 96;
    mItemOffset = dy % 96;
    offsetChildrenVertical(-dy);
    return dy;
  }

  @Override public boolean canScrollVertically() {
    return true;
  }

  @Override public void layoutDecorated(View child, int left, int top, int right, int bottom) {
    //Log.d(MyLayoutManager.class.getSimpleName(), " Layout Child: " + child.toString() + " left: " + left + " top: " + top + " right: " + right + " bottom: " + bottom);
    super.layoutDecorated(child, left, top, right, bottom);
  }
}
