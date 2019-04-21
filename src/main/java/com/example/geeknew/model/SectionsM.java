package com.example.geeknew.model;

import com.example.geeknew.api.MyServer;
import com.example.geeknew.base.BaseModel;
import com.example.geeknew.bean.DiailyNewsBean;
import com.example.geeknew.bean.SectionsBean;
import com.example.geeknew.net.BaseObserver;
import com.example.geeknew.net.HttpUtils;
import com.example.geeknew.net.ResultCallBack;
import com.example.geeknew.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SectionsM extends BaseModel {
    public void getData(final ResultCallBack callBack){
        MyServer myServer= HttpUtils.getInstance().getApiserver(MyServer.BsreUrl,MyServer.class);
        Observable<SectionsBean> news = myServer.getSections();
        news.compose(RxUtils.<SectionsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SectionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(SectionsBean diailyNewsBean) {
                        callBack.onSuccess(diailyNewsBean);
                    }
                });
    }
}
