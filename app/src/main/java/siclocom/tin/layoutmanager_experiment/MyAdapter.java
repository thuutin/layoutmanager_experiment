package siclocom.tin.layoutmanager_experiment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tin on 4/5/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

  public MyAdapter() {
    setHasStableIds(true);
  }

  @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    return new VH(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
  }

  @Override public void onBindViewHolder(VH holder, int position) {
    holder.tv.setText(String.valueOf(position));
  }

  @Override public long getItemId(int position) {
    return position;
  }


  @Override public int getItemCount() {
    return 20000;
  }

  public static class VH extends RecyclerView.ViewHolder {
    TextView tv;
    public VH(View itemView) {
      super(itemView);
      tv = (TextView) itemView.findViewById(android.R.id.text1);
    }
  }

}
