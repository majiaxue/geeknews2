package com.example.geeknew.presenter;

import com.example.geeknew.base.BasePresenter;
import com.example.geeknew.bean.BeforeNewsBean;
import com.example.geeknew.bean.DiailyNewsBean;
import com.example.geeknew.callback.BeforeNewsCallBack;
import com.example.geeknew.model.DailyNewsM;
import com.example.geeknew.net.ResultCallBack;
import com.example.geeknew.view.DiailyView;

public class DiailyP extends BasePresenter<DiailyView> {
    private DailyNewsM dailyNewsM;
    @Override
    protected void initModel() {
        dailyNewsM=new DailyNewsM();
        mModels.add(dailyNewsM);
    }
    public void getData(){
        dailyNewsM.getData(new ResultCallBack<DiailyNewsBean>() {
            @Override
            public void onSuccess(DiailyNewsBean bean) {
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

    public void getDailyNewsData() {

    }

    public void getBeforeNewsData(String date) {
        dailyNewsM.getBeforeNewsData(date, new BeforeNewsCallBack() {
            @Override
            public void onBeforeSuccess(BeforeNewsBean beforeNewsBean) {
                if (mMvpView != null){
                    mMvpView.onBeforeNewsData(beforeNewsBean);
                }
            }
        });
    }
}
