package com.example.wto.view;

public interface MainView<T> {
    void OnNext(T t);
    void OnError(String log);
}
