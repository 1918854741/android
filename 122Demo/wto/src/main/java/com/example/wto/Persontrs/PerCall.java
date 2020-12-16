package com.example.wto.Persontrs;

import com.example.wto.bean.BannerBean;

public interface PerCall<T> {
    void OnNext(T t);
    void OnError(String log);
}
