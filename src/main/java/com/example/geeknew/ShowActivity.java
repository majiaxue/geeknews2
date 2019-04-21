package com.example.geeknew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.geeknew.adapter.RecGoldAdapter;
import com.example.geeknew.base.BaseActivity;
import com.example.geeknew.base.Constants;
import com.example.geeknew.bean.GoldBean;
import com.example.geeknew.presenter.EmptyP;
import com.example.geeknew.view.EmptyV;
import com.example.geeknew.widget.SimpleItemTouchCallBack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends BaseActivity<EmptyV, EmptyP> implements EmptyV {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<GoldBean> mTitles;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {

        toolbar.setTitle(R.string.special_show);
        toolbar.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAct();
            }
        });
        mTitles= (ArrayList<GoldBean>) getIntent().getSerializableExtra(Constants.DATA);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        RecGoldAdapter adapter=new RecGoldAdapter(mTitles);
        recyclerview.setAdapter(adapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        //拖拽移动和左滑删除
        SimpleItemTouchCallBack back = new SimpleItemTouchCallBack(adapter);
        back.setSwipeEnable(false);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(back);
        itemTouchHelper.attachToRecyclerView(recyclerview);
    }

    private void finishAct() {
        Intent intent=new Intent();
        intent.putExtra(Constants.DATA,mTitles);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAct();
    }
}
