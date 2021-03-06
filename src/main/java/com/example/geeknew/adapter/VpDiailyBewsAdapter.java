package com.example.geeknew.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.geeknew.base.BaseFragment;

import java.util.ArrayList;

public class VpDiailyBewsAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<Integer> mTitles;

    public VpDiailyBewsAdapter(Context context, FragmentManager fm,
                          ArrayList<BaseFragment> fragments, ArrayList<Integer> titles) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        mTitles = titles;
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
        return mContext.getResources().getString(mTitles.get(position));
    }
}
