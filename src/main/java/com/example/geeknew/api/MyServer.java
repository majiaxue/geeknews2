package com.example.geeknew.api;

import com.example.geeknew.bean.BeforeNewsBean;
import com.example.geeknew.bean.DiailyNewsBean;
import com.example.geeknew.bean.HotBean;
import com.example.geeknew.bean.SectionsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyServer {
    public String BsreUrl="http://news-at.zhihu.com/api/4/";
    @GET("news/latest")
    Observable<DiailyNewsBean> getDiailyNews();

    @GET("sections")
    Observable<SectionsBean> getSections();

    @GET("news/hot")
    Observable<HotBean> getHot();

    @GET("news/before/{date}")
    Observable<BeforeNewsBean> getBeforeNewsData(@Path("date") String date);

}
