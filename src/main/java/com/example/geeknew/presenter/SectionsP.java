package com.example.geeknew.presenter;

import com.example.geeknew.base.BasePresenter;
import com.example.geeknew.bean.DiailyNewsBean;
import com.example.geeknew.bean.SectionsBean;
import com.example.geeknew.model.DailyNewsM;
import com.example.geeknew.model.SectionsM;
import com.example.geeknew.net.ResultCallBack;
import com.example.geeknew.view.SectionsV;

public class SectionsP extends BasePresenter<SectionsV> {
    private SectionsM dailyNewsM;
    @Override
    protected void initModel() {
        dailyNewsM=new SectionsM();
        mModels.add(dailyNewsM);
    }
    public void getData() {
        dailyNewsM.getData(new ResultCallBack<SectionsBean>() {
            @Override
            public void onSuccess(SectionsBean bean) {
                if (bean != null) {
                    if (mMvpView != null) {
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
