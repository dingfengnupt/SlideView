package com.df.slideview;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windy.slideview.RecyclingPagerAdapter;

/**
 * Created by dingfeng on 2016/8/8.
 */
public class MyAdapter extends RecyclingPagerAdapter {

    private Context mContext;
    private String[] mUrls;
    private int size;
    private boolean isInfiniteLoop = false;

    public MyAdapter(Context context, String[] urls) {
        this.mContext = context;
        this.mUrls = urls;
        this.size = urls.length;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : mUrls.length;
    }

    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    // 设置循环
    public void setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pager, null);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mUrls[getPosition(position)]).into(holder.image);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "onClick: " + getPosition(position), Snackbar.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


    private static class ViewHolder {
        ImageView image;
    }

}
