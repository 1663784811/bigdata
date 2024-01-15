package com.cyyaw.iot.activity.fragment;

import com.cyyaw.iot.R;


public class NewsFragment extends BaseFragment {

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fr_simple_card;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

}