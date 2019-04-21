package com.example.geeknew.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknew.R;
import com.example.geeknew.adapter.RecDiailyNewsAdapter;
import com.example.geeknew.adapter.RecSectionsAdapter;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.bean.DiailyNewsBean;
import com.example.geeknew.bean.SectionsBean;
import com.example.geeknew.presenter.EmptyP;
import com.example.geeknew.presenter.SectionsP;
import com.example.geeknew.view.EmptyV;
import com.example.geeknew.view.SectionsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionFragment extends BaseFragment<SectionsV, SectionsP> implements SectionsV {

    private RecSectionsAdapter adapter;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    Unbinder unbinder;

    @Override
    protected void initView() {
        rlv.setLayoutManager(new GridLayoutManager(getContext(),2));
        ArrayList<SectionsBean.DataBean> dataBeans = new ArrayList<>();
        adapter = new RecSectionsAdapter(getContext(),dataBeans);
        rlv.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_section;
    }

    @Override
    protected SectionsP initPresenter() {
        return new SectionsP();
    }

    @Override
    public void setData(SectionsBean bean) {
        adapter.setData(bean);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }
}
