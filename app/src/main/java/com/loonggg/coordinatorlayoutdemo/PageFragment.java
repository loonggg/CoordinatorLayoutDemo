package com.loonggg.coordinatorlayoutdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private RecyclerView lv;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, null);
        lv = (RecyclerView) view.findViewById(R.id.lv);
        // 创建一个线性布局管理器

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        // 设置布局管理器

        lv.setLayoutManager(layoutManager);

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        lv.setAdapter(new MyAdapter(list));
        return view;
    }
}
