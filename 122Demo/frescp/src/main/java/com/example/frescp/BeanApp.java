package com.example.frescp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class BeanApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
