package com.example.admin.recyclerviewdemo.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by admin on 2016/12/16.
 * 优化ViewHolder
 * li.
 */
public class OptimizeViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> views;
    private final Context context;
    private View convertView;

    protected OptimizeViewHolder(Context context, View view) {
        super(view);
        this.context = context;
        this.views = new SparseArray<View>();
        convertView = view;
    }
    protected <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
    public OptimizeViewHolder setText(int viewId, CharSequence value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }
    public OptimizeViewHolder setImageUrl(int viewId, String imageUrl) {
        ImageView view = retrieveView(viewId);
        Glide.with(context)
                .load(imageUrl)
                .crossFade()
                .into(view);
        return this;
    }
}
