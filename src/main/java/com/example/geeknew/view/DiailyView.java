package com.example.geeknew.view;

import com.example.geeknew.base.BaseMvpView;
import com.example.geeknew.bean.BeforeNewsBean;
import com.example.geeknew.bean.DiailyNewsBean;

public interface DiailyView extends BaseMvpView {
    void setData(DiailyNewsBean bean);

    void onBeforeNewsData(BeforeNewsBean beforeNewsBean);
}
