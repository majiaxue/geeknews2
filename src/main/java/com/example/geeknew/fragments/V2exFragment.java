package com.example.geeknew.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknew.R;
import com.example.geeknew.adapter.VpV2exAdapter;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.bean.V2exTabBean;
import com.example.geeknew.presenter.EmptyP;
import com.example.geeknew.view.EmptyV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class V2exFragment extends BaseFragment<EmptyV, EmptyP> {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private String mUrl = "https://www.v2ex.com/";
    private static final String TAG = "V2exFragment";
    private ArrayList<Fragment> list;
    private VpV2exAdapter adapter;
    private ArrayList<V2exTabBean> tabList = new ArrayList<>();

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.v2ex_fragment;
    }


    @Override
    protected void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(mUrl).get();
                    Element tabs = document.select("div#Tabs").first();
                    Elements allTabs = tabs.select("a[href]");

                    tabList = new ArrayList<>();
                    for (Element element : allTabs) {
                        String linkHref = element.attr("href");
                        String LinkText = element.text();
                        Log.d(TAG, "linkHref:" + linkHref + ",LinkText:" + LinkText);
                        V2exTabBean v2exTabBean = new V2exTabBean(linkHref, LinkText);
                        tabList.add(v2exTabBean);

                    }
                    Log.d(TAG, tabList + "");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (tabList != null && tabList.size() > 0) {
                                list = new ArrayList<>();
                                for (int i = 0; i < tabList.size(); i++) {
                                    list.add(new V2exItemFragment(tabList.get(i).link));
                                    tab.addTab(tab.newTab().setText(tabList.get(i).tab));
                                }
                                adapter = new VpV2exAdapter(getChildFragmentManager(), list);
                                vp.setAdapter(adapter);
                                tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                    @Override
                                    public void onTabSelected(TabLayout.Tab tab) {
                                        vp.setCurrentItem(tab.getPosition());
                                    }

                                    @Override
                                    public void onTabUnselected(TabLayout.Tab tab) {

                                    }

                                    @Override
                                    public void onTabReselected(TabLayout.Tab tab) {

                                    }
                                });
                                vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
                            }
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
