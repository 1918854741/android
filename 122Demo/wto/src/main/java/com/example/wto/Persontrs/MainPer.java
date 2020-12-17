package com.example.wto.Persontrs;

import com.example.wto.bean.BannerBean;
import com.example.wto.bean.FoodBean;
import com.example.wto.model.Mainmodel;
import com.example.wto.view.MainView;

public class MainPer {
     public MainView mainView;
    private final Mainmodel mainmodel;

    public MainPer(MainView mainView) {
        this.mainView = mainView;
        mainmodel = new Mainmodel();
    }
    public void getdata(){
         mainmodel.getM1(new PerCall<BannerBean>() {
             @Override
             public void OnNext(BannerBean bean) {
                 mainView.OnNext(bean);
             }

             @Override
             public void OnError(String log) {
                   mainView.OnError(log);
             }
         });
    }

    public void getdata2() {
            mainmodel.getM2(new PerCall<FoodBean>() {
                @Override
                public void OnNext(FoodBean foodBean) {
                    mainView.OnNext(foodBean);
                }

                @Override
                public void OnError(String log) {
                    mainView.OnError(log);
                }
            });
        }
    }

