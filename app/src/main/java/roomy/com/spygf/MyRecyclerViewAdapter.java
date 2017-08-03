
package roomy.com.spygf;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;
  Context  ctx;
    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView appName;
        TextView appPackage;
        TextView time;
        ImageView appImage;

        public DataObjectHolder(View itemView) {
            super(itemView);
            appName = (TextView) itemView.findViewById(R.id.Appname);
            appPackage = (TextView) itemView.findViewById(R.id.Package);
            time = (TextView) itemView.findViewById(R.id.Time);
            appImage=(ImageView) itemView.findViewById(R.id.imageView);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_cardview, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.appName.setText(mDataset.get(position).getmText1());
        holder.appPackage.setText(mDataset.get(position).getmText2());
        holder.time.setText(mDataset.get(position).getmText3());
        //Drawable icon = getPackageManager().getApplicationIcon(mDataset.get(position).getmText4());
        //ctx.getResources().getDrawable(R.id.)
        holder.appImage.setImageDrawable(icon);
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}