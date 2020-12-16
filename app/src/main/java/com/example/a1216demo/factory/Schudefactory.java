package com.example.a1216demo.factory;

public class Schudefactory {
    public static final int schdule_threadPoll=0;
    public static SchuderThreadPool getFactory(int type){
        switch(type){
           case schdule_threadPoll:

           return SchuderThreadPool.getInstance();
        }
        return null;
    }
}
