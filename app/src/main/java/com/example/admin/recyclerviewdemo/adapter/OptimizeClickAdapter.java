package com.example.admin.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by admin on 2016/12/29.
 */

public class OptimizeClickAdapter extends VBaseRecylerAdapter<OptimizeClickAdapter.ViewHolder> {

    private List<String> mList;

    public void setmList(List<String> mList) {
        this.mList = mList;
    }

    private Context mContext;

    public OptimizeClickAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    protected OptimizeClickAdapter.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new OptimizeClickAdapter.ViewHolder(view);
    }

    protected void onBindDataViewHolder(OptimizeClickAdapter.ViewHolder holder, int position) {
        holder.tvContent.setText(mList.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    protected int getItemCountNum() {
        return mList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            tvContent = (TextView) view.findViewById(R.id.item_text);
            imageView = (ImageView) view.findViewById(R.id.item_image);
        }
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


