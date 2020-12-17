package com.example.comxiaozhao.net;

import com.example.comxiaozhao.bean.ExBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface AserviceAPI {
    String ex_list="https://www.wanandroid.com/";
    @GET("tree/json")
    Flowable<ExBean>getdata();
}
