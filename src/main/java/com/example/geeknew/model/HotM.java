package com.example.geeknew.model;

import com.example.geeknew.api.MyServer;
import com.example.geeknew.base.BaseModel;
import com.example.geeknew.bean.HotBean;
import com.example.geeknew.bean.SectionsBean;
import com.example.geeknew.net.BaseObserver;
import com.example.geeknew.net.HttpUtils;
import com.example.geeknew.net.ResultCallBack;
import com.example.geeknew.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HotM extends BaseModel {
    public void getData(final ResultCallBack callBack){
        MyServer myServer= HttpUtils.getInstance().getApiserver(MyServer.BsreUrl,MyServer.class);
        Observable<HotBean> news = myServer.getHot();
        news.compose(RxUtils.<HotBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HotBean diailyNewsBean) {
                        callBack.onSuccess(diailyNewsBean);
                    }
                });
    }
}
