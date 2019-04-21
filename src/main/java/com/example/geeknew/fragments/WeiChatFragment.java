package com.example.geeknew.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknew.R;
import com.example.geeknew.adapter.RecChatAdapter;
import com.example.geeknew.base.BaseFragment;
import com.example.geeknew.bean.WChatBean;
import com.example.geeknew.presenter.ChatP;
import com.example.geeknew.presenter.EmptyP;
import com.example.geeknew.view.ChatV;
import com.example.geeknew.view.EmptyV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
//马佳雪
public class WeiChatFragment extends BaseFragment<ChatV, ChatP> implements ChatV {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private RecChatAdapter adapter;
    private ArrayList<WChatBean.NewslistBean> list;

    @Override
    protected ChatP initPresenter() {
        return new ChatP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.weichat_fragment;
    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        adapter=new RecChatAdapter(list,getContext());
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void setData(WChatBean bean) {
        adapter.setData(bean);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }
}
