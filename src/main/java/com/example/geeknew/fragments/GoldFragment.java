package com.example.geeknew.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geeknew.R;
import com.example.geeknew.ShowActivity;
import com.example.geeknew.adapter.VpGoldAdapter;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.base.Constants;
import com.example.geeknew.bean.GoldBean;
import com.example.geeknew.presenter.EmptyP;
import com.example.geeknew.presenter.GoldP;
import com.example.geeknew.view.EmptyV;
import com.example.geeknew.view.GoldV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GoldFragment extends BaseFragment<GoldV, GoldP> implements GoldV {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<GoldBean> mTitles;

    @Override
    protected GoldP initPresenter() {
        return new GoldP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gold_fragment;
    }

    @Override
    protected void initView() {
        initTitles();
        setFragments();
    }

    private void setFragments() {
        initFragments();
        VpGoldAdapter adapter = new VpGoldAdapter(getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(adapter);
        tablayout.setupWithViewPager(vp);
    }

    @OnClick({R.id.iv})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv:
                go2ShowActivity();
                break;
        }
    }

    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA, mTitles);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null) {
            if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
                mTitles = (ArrayList<GoldBean>) data.getSerializableExtra(Constants.DATA);
                setFragments();
            }
        }

    }

    private void initFragments() {
        mFragments=new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            GoldBean bean = mTitles.get(i);
            if (bean.isChecked){
                mFragments.add(GoldItemFragment.newInstance(bean.title));
            }
        }
    }

    private void initTitles() {
        mTitles=new ArrayList<>();
        mTitles.add(new GoldBean("Android",true));
        mTitles.add(new GoldBean("iOS",true));
        mTitles.add(new GoldBean("设计",true));
        mTitles.add(new GoldBean("工具资源",true));
        mTitles.add(new GoldBean("产品",true));
        mTitles.add(new GoldBean("阅读",true));
        mTitles.add(new GoldBean("前端",true));
        mTitles.add(new GoldBean("后端",true));
    }
}
