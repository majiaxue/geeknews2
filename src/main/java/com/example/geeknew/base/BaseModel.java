package com.example.geeknew.base;

import io.reactivex.disposables.CompositeDisposable;

public class BaseModel {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public void onDestory() {
        //切换所有的Disposable对象
        mCompositeDisposable.clear();
    }
}
