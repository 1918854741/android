package com.example.a1216demo.factory;

import java.util.concurrent.TimeUnit;

public abstract class ThreadPoolinteface {
     public void executeTask(Runnable runnable){

     }
     public void executeTimerTask(Runnable runnable, long fretinme, long time, TimeUnit timeUnit){

     }
     public abstract void removeTask();
     public void removeTask(Runnable runnable){

     }
}
