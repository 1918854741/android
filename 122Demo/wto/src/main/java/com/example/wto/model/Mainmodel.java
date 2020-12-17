package com.example.wto.model;

import com.example.wto.AvSerivce;
import com.example.wto.Persontrs.PerCall;
import com.example.wto.bean.BannerBean;
import com.example.wto.bean.FoodBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mainmodel {
    public void getM1(final PerCall<BannerBean> perCall) {
        new Retrofit.Builder()
                .baseUrl(AvSerivce.str1)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AvSerivce.class)
                .getdata1()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<BannerBean>() {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        perCall.OnNext(bannerBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                       perCall.OnError(t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getM2(final PerCall<FoodBean>perCall) {
        new Retrofit.Builder()
                .baseUrl(AvSerivce.str2)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AvSerivce.class)
                .getdata2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<FoodBean>() {
                    @Override
                    public void onNext(FoodBean foodBean) {
                       perCall.OnNext(foodBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                       perCall.OnError(t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
