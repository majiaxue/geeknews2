package com.example.geeknew.presenter;

import com.example.geeknew.base.BasePresenter;
import com.example.geeknew.bean.HotBean;
import com.example.geeknew.bean.SectionsBean;
import com.example.geeknew.model.HotM;
import com.example.geeknew.model.SectionsM;
import com.example.geeknew.net.ResultCallBack;
import com.example.geeknew.view.HotV;

public class HotP extends BasePresenter<HotV> {
    private HotM dailyNewsM;
    @Override
    protected void initModel() {
        dailyNewsM=new HotM();
        mModels.add(dailyNewsM);
    }
    public void getData() {
        dailyNewsM.getData(new ResultCallBack<HotBean>() {
            @Override
            public void onSuccess(HotBean bean) {
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
