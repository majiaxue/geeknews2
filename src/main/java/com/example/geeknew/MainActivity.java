package com.example.geeknew;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.geeknew.base.BaseActivity;
import com.example.geeknew.fragments.AboutFragment;
import com.example.geeknew.fragments.CollectFragment;
import com.example.geeknew.fragments.GankFragment;
import com.example.geeknew.fragments.GoldFragment;
import com.example.geeknew.fragments.SettingsFragment;
import com.example.geeknew.fragments.V2exFragment;
import com.example.geeknew.fragments.WeiChatFragment;
import com.example.geeknew.fragments.ZhiHuFragment;
import com.example.geeknew.presenter.MainP;
import com.example.geeknew.util.ToastUtil;
import com.example.geeknew.view.MainV;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//马佳雪
public class MainActivity extends BaseActivity<MainV, MainP> implements MainV {


    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.fragment_continer)
    FrameLayout fragmentContiner;
    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.toolbar_container)
    FrameLayout toolbarContainer;
    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> mTitles;
    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WEICHAT = 1;
    private final int TYPE_GANK = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_V2EX = 4;
    private final int TYPE_COLLECT = 5;
    private final int TYPE_SETTINGS = 6;
    private final int TYPE_ABOUT = 7;

    private int mLastFragmnetPosition = 0;
    private MenuItem mSearchItem;

    @Override
    protected MainP initPresenter() {
        return new MainP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initTitles();
        manager = getSupportFragmentManager();
        toolBar.setTitleTextColor(getResources().getColor(R.color.white));
        toolBar.setTitle(mTitles.get(0));
        setSupportActionBar(toolBar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolBar, R.string.about, R.string.about);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        dl.addDrawerListener(toggle);
        toggle.syncState();
        initFragments();
        addZhiHuFragment();
    }

    private void addZhiHuFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_continer, fragments.get(0));
        transaction.commit();

    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(R.string.zhihu);
        mTitles.add(R.string.weichat);
        mTitles.add(R.string.gank);
        mTitles.add(R.string.gold);
        mTitles.add(R.string.v2ex);
        mTitles.add(R.string.collect);
        mTitles.add(R.string.settings);
        mTitles.add(R.string.about);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new ZhiHuFragment());
        fragments.add(new WeiChatFragment());
        fragments.add(new GankFragment());
        fragments.add(new GoldFragment());
        fragments.add(new V2exFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SettingsFragment());
        fragments.add(new AboutFragment());
    }

    @Override
    protected void initListener() {
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId != R.id.info_title && itemId != R.id.info_other) {
                    menuItem.setChecked(true);
                    switch (itemId) {
                        case R.id.zhuhu:
                            switchFragments(TYPE_ZHIHU);
                            break;
                        case R.id.weichat:
                            switchFragments(TYPE_WEICHAT);
                            break;
                        case R.id.gank:
                            switchFragments(TYPE_GANK);
                            break;
                        case R.id.gold:
                            switchFragments(TYPE_GOLD);
                            break;
                        case R.id.v2ex:
                            switchFragments(TYPE_V2EX);
                            break;
                        case R.id.collect:
                            switchFragments(TYPE_COLLECT);
                            break;
                        case R.id.settings:
                            switchFragments(TYPE_SETTINGS);
                            break;
                        case R.id.about:
                            switchFragments(TYPE_ABOUT);
                            break;
                    }
                    dl.closeDrawer(Gravity.LEFT);
                } else {
                    menuItem.setChecked(false);
                }
                return false;
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                ToastUtil.showShort("展开");
            }

            @Override
            public void onSearchViewClosed() {
                ToastUtil.showShort("关闭");
            }
        });
    }

    private void switchFragments(int type) {
        Fragment mfragment = fragments.get(type);
        Fragment hidefragment = fragments.get(mLastFragmnetPosition);
        FragmentTransaction transaction = manager.beginTransaction();
        if (!mfragment.isAdded()) {
            transaction.add(R.id.fragment_continer, mfragment);
        }
        transaction.hide(hidefragment);
        transaction.show(mfragment);
        transaction.commit();

        mLastFragmnetPosition = type;

        if (type==TYPE_WEICHAT||type==TYPE_GANK){
            mSearchItem.setVisible(true);
        }else {
            mSearchItem.setVisible(false);
        }
        toolBar.setTitle(mTitles.get(type));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        mSearchItem=menu.findItem(R.id.action_search);
        mSearchItem.setVisible(false);
        searchView.setMenuItem(mSearchItem);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()){
            searchView.closeSearch();
        }else {
            super.onBackPressed();
        }

    }
}
