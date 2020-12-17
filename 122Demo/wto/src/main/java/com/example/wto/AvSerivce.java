package com.example.wto;

import com.example.wto.bean.BannerBean;
import com.example.wto.bean.FoodBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface AvSerivce {
    String str1="https://www.wanandroid.com/";
    @GET("banner/json")
    Flowable<BannerBean>getdata1();
    String str2="http://www.qubaobei.com/ios/cf/";
    @GET("dish_list.php?stage_id=1&limit=20&page=1")
    Flowable<FoodBean>getdata2();
}
