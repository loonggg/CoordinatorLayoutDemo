package com.loonggg.coordinatorlayoutdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by loongggdroid on 2016/5/19.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    // 数据集
    private List<String> mDataset;

    public MyAdapter(List<String> dataset) {
        super();
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView

        View view = View.inflate(viewGroup.getContext(), android.R.layout.simple_list_item_1, null);

        // 创建一个ViewHolder

        ViewHolder holder = new ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        // 绑定数据到ViewHolder上

        viewHolder.mTextView.setText(mDataset.get(i));

    }

    @Override
    public int getItemCount() {

        return mDataset.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            mTextView = (TextView) itemView;

        }

    }

}



