package com.example.admin.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.admin.recyclerviewdemo.R;
import com.example.admin.recyclerviewdemo.utils.OptimizeBaseAdapter;
import com.example.admin.recyclerviewdemo.utils.OptimizeViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11158 on 2016-11-29.
 */

public class OptimizeAdapter extends OptimizeBaseAdapter<String>  implements View.OnClickListener{

    private List<String> mList;

    public void setmList(List<String> mList) {
        this.mList = mList;
    }
    private RecyclerView recyclerView;


    public interface OnRecyclerViewLongItemClickListener {
        void onLongItemClick(View view, int position);
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public OnRecyclerViewItemClickListener mOnItemClickListener = null;//点击
    public OnRecyclerViewLongItemClickListener mOnLongItemClickListener = null;//长按

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public void setOnLongItemClickListener(OnRecyclerViewLongItemClickListener listener) {
        this.mOnLongItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v,recyclerView.getChildAdapterPosition((View) v.getParent()));
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongItemClickListener != null) {
                    mOnLongItemClickListener.onLongItemClick(v,recyclerView.getChildAdapterPosition((View) v.getParent()));
                }
                return true;
            }
        });
    }

    public OptimizeAdapter(Context context, int resource, List<String> mlist) {
        super(context,resource, (ArrayList<String>) mlist);
    }

    @Override
    protected void convert(OptimizeViewHolder helper, String item) {
        helper.setText(R.id.item_text, item);
    }

    public void Add(int position,String data){
        mList.add(position, data);
        notifyItemInserted(position);
    }
    public void Remove(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }
    public void Change(int position,String data){
        mList.remove(position);
        mList.add(position,data);
        notifyItemChanged(position);
    }
}
