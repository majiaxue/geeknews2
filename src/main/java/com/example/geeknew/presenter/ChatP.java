package com.example.geeknew.presenter;

import com.example.geeknew.base.BasePresenter;
import com.example.geeknew.bean.WChatBean;
import com.example.geeknew.model.ChatModel;
import com.example.geeknew.net.ResultCallBack;
import com.example.geeknew.view.ChatV;

public class ChatP extends BasePresenter<ChatV> {
    private ChatModel model;
    @Override
    protected void initModel() {
        model=new ChatModel();
        mModels.add(model);
    }
    public void getData(){
        model.getData(new ResultCallBack<WChatBean>() {
            @Override
            public void onSuccess(WChatBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
