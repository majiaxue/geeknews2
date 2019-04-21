package com.example.geeknew.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknew.R;
import com.example.geeknew.adapter.VpDiailyBewsAdapter;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.presenter.EmptyP;
import com.example.geeknew.presenter.ZhiHuP;
import com.example.geeknew.view.EmptyV;
import com.example.geeknew.view.ZhiHuV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ZhiHuFragment extends BaseFragment<ZhiHuV, ZhiHuP> {

    @BindView(R.id.tab)
    TabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Integer> mTitles;
    private ArrayList<BaseFragment> mFragments;

    @Override
    protected ZhiHuP initPresenter() {
        return new ZhiHuP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.zhihu_fragment;
    }

    @Override
    protected void initView() {
        initTitles();
        initFragments();
        VpDiailyBewsAdapter adapter = new VpDiailyBewsAdapter(getContext(),getChildFragmentManager(),
                mFragments,mTitles);
        mVp.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new DiarilyNewsFragment());
        mFragments.add(new SectionFragment());
        mFragments.add(new HotFragment());

    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(R.string.dailyNews);
        mTitles.add(R.string.sections);
        mTitles.add(R.string.hot);
    }



}
