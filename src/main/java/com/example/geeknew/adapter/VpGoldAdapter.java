package com.example.geeknew.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.bean.GoldBean;

import java.util.ArrayList;

public class VpGoldAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<GoldBean> mTitles;
    private ArrayList<String> mNewTitles=new ArrayList<>();


    public VpGoldAdapter( FragmentManager fm, ArrayList<BaseFragment> fragments, ArrayList<GoldBean> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
        for (int i = 0; i <mTitles.size() ; i++) {
            GoldBean bean = mTitles.get(i);
            if (bean.isChecked){
                mNewTitles.add(bean.title);
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mNewTitles.get(position);
    }
}
