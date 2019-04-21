package com.example.geeknew.fragments;

import com.example.geeknew.R;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.presenter.EmptyP;
import com.example.geeknew.view.EmptyV;

public class AboutFragment extends BaseFragment<EmptyV,EmptyP> {

    @Override
    protected EmptyP initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.about_fragment;
    }
}
