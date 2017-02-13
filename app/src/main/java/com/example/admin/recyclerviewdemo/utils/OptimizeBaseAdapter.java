package com.example.admin.recyclerviewdemo.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by admin on 2016/12/16.
 * 优化adapter
 * li.
 */

public abstract class OptimizeBaseAdapter<T> extends RecyclerView.Adapter<OptimizeViewHolder>{

    private int layoutResId;
    private List<T> data;
    private Context context;

    public OptimizeBaseAdapter(Context context, int resource, ArrayList<T> objects) {
        this.context = context;
        this.layoutResId = resource;
        this.data = objects;
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public OptimizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new OptimizeViewHolder(context, item);
    }
    @Override
    public void onBindViewHolder(OptimizeViewHolder holder, final int position) {
        convert(holder, data.get(position));

    }
    protected abstract void convert(OptimizeViewHolder helper, T item);
}
