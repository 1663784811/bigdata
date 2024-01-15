package com.cyyaw.iot.activity.fragment;

import com.cyyaw.iot.R;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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