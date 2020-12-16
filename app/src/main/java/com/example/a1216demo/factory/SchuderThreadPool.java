package com.example.a1216demo.factory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchuderThreadPool extends ThreadPoolinteface{
    private static SchuderThreadPool sSchuderThreadPool;
    private ScheduledExecutorService mScheduledExecutorService;
    @Override
    public void executeTimerTask(Runnable runnable, long fretinme, long time, TimeUnit timeUnit) {
             mScheduledExecutorService.scheduleWithFixedDelay(runnable,fretinme,time,timeUnit);
    }
    private SchuderThreadPool (){
        mScheduledExecutorService= Executors.newSingleThreadScheduledExecutor();
    }
    public synchronized static SchuderThreadPool getInstance(){
        if(sSchuderThreadPool==null){
            synchronized (SchuderThreadPool.class){
                if(sSchuderThreadPool!=null){
                    sSchuderThreadPool=new SchuderThreadPool();
                  }
            }
          }
        return sSchuderThreadPool;
    }
    @Override
    public void removeTask() {

    }
}
