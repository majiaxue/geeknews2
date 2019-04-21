package com.example.geeknew.api;

import com.example.geeknew.bean.WChatBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WeChatServer {

    public String URL="http://api.tianapi.com/wxnew/";
    @GET("?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1")
    Observable<WChatBean>getWeChat();
}
