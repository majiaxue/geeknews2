package com.example.geeknew.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknew.R;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhiHuDailyNewsFragment extends BaseFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zhi_hu_daily_news, container, false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

}
