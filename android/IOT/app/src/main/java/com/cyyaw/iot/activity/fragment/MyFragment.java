package com.cyyaw.iot.activity.fragment;

import com.cyyaw.iot.R;


public class MyFragment extends BaseFragment {


    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
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