package com.example.admin.recyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.recyclerviewdemo.R;


/**
 * @Author: seven.
 * @Date: 2016/7/14 18:07
 * @E-mail: 529797149@qq.com
 */
public abstract class VBaseRecylerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements
        View.OnClickListener, View.OnLongClickListener  {


    public static interface OnItemClickListener {
        void onItemClick(View view, int dataIndex);
    }

    public static interface OnItemLongClickListener {
        void onItemLongClick(View view, int dataIndex);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateDataViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

        onBindDataViewHolder(holder , position);
        boolean setBg = false;
        if (onItemClickListener != null  ) {
                setBg = true;
            if (setBg) {
                holder.itemView.setTag(R.id.id_data_index, Integer.valueOf(position));
                holder.itemView.setOnClickListener(this);
            }
        }

        if (onItemLongClickListener != null) {
            setBg = true;
            holder.itemView.setTag(R.id.id_data_index, Integer.valueOf(position));
            holder.itemView.setOnLongClickListener(this);
        }
        if (setBg && itemBgSelector != 0) {
            holder.itemView.setBackgroundResource(itemBgSelector);
        }

    }

    protected abstract VH onCreateDataViewHolder(ViewGroup parent, int viewType);


    protected abstract void onBindDataViewHolder(VH holder, int position);



    @Override
    public int getItemCount() {
        return getItemCountNum();
    }

    protected abstract int getItemCountNum();

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, ((Integer) v.getTag(R.id.id_data_index)).intValue());
    }

    @Override
    public boolean onLongClick(View v) {
        onItemLongClickListener.onItemLongClick(v, ((Integer) v.getTag(R.id.id_data_index)).intValue());
        return true;
    }


    private OnItemClickListener onItemClickListener;

    private OnItemLongClickListener onItemLongClickListener;

    private int itemBgSelector = R.drawable.v_list_item_selector;
}
