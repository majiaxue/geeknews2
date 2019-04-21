package com.example.geeknew.model;

import com.example.geeknew.api.WeChatServer;
import com.example.geeknew.base.BaseModel;
import com.example.geeknew.bean.WChatBean;
import com.example.geeknew.net.BaseObserver;
import com.example.geeknew.net.HttpUtils;
import com.example.geeknew.net.ResultCallBack;
import com.example.geeknew.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ChatModel extends BaseModel {
    public void getData(final ResultCallBack callBack){
        WeChatServer server = HttpUtils.getInstance().getApiserver(WeChatServer.URL, WeChatServer.class);
        Observable<WChatBean> weChat = server.getWeChat();
        weChat.compose(RxUtils.<WChatBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WChatBean bean) {
                        callBack.onSuccess(bean);
                    }
                });
    }
}
