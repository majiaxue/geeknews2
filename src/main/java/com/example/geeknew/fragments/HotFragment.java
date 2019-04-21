package com.example.geeknew.fragments;


import android.media.DrmInitData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknew.R;
import com.example.geeknew.adapter.HotAdapter;
import com.example.geeknew.adapter.RecSectionsAdapter;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.bean.HotBean;
import com.example.geeknew.bean.SectionsBean;
import com.example.geeknew.presenter.HotP;
import com.example.geeknew.view.HotV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment<HotV, HotP> implements HotV {

    private HotAdapter adapter;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    Unbinder unbinder;

    @Override
    protected void initView() {
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<HotBean.RecentBean> dataBeans = new ArrayList<>();
        adapter = new HotAdapter(dataBeans,getContext());
        rlv.setAdapter(adapter);
    }



    @Override
    protected HotP initPresenter() {
        return new HotP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void setData(HotBean bean) {
        adapter.getData(bean);
    }
    @Override
    protected void initData() {
        mPresenter.getData();
    }

}
