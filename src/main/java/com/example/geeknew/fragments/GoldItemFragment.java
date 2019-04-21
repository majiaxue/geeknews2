package com.example.geeknew.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geeknew.R;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.base.Constants;
import com.example.geeknew.presenter.EmptyP;
import com.example.geeknew.view.EmptyV;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldItemFragment extends BaseFragment<EmptyV, EmptyP> implements EmptyV {

    @BindView(R.id.tv)
    TextView tv;

    public static GoldItemFragment newInstance(String text) {
        GoldItemFragment fragment = new GoldItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA, text);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_item;
    }

    @Override
    protected void initView() {

        Bundle arguments = getArguments();
        String string = arguments.getString(Constants.DATA);
        tv.setText(string);

    }

}
