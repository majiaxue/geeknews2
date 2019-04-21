package com.example.geeknew.model;

import android.util.Log;

import com.example.geeknew.api.MyServer;
import com.example.geeknew.base.BaseModel;
import com.example.geeknew.bean.BeforeNewsBean;
import com.example.geeknew.bean.DiailyNewsBean;
import com.example.geeknew.bean.SectionsBean;
import com.example.geeknew.callback.BeforeNewsCallBack;
import com.example.geeknew.net.BaseObserver;
import com.example.geeknew.net.HttpUtils;
import com.example.geeknew.net.ResultCallBack;
import com.example.geeknew.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DailyNewsM extends BaseModel {
    private static final String TAG = "kkk";

    public void getData(final ResultCallBack callBack){
        MyServer myServer= HttpUtils.getInstance().getApiserver(MyServer.BsreUrl,MyServer.class);
        Observable<DiailyNewsBean> news = myServer.getDiailyNews();
        news.compose(RxUtils.<DiailyNewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DiailyNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DiailyNewsBean diailyNewsBean) {
                        callBack.onSuccess(diailyNewsBean);
                    }
                });
    }
    public void getBeforeNewsData(String date, final BeforeNewsCallBack callBack) {
        MyServer apiserver = HttpUtils.getInstance().getApiserver(MyServer.BsreUrl, MyServer.class);

        Observable<BeforeNewsBean> observable = apiserver.getBeforeNewsData(date);

        observable.compose(RxUtils.<BeforeNewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BeforeNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BeforeNewsBean beforeNewsBean) {
                        Log.i(TAG, "onNext: "+beforeNewsBean.getStories().size());
                        callBack.onBeforeSuccess(beforeNewsBean);
                    }
                });
    }
}
