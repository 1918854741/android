package com.example.a122demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static org.greenrobot.eventbus.ThreadMode.ASYNC;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
       if(!EventBus.getDefault().isRegistered(this)){
           EventBus.getDefault().register(this);
       }
    }
    //注销


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        bt_home = (Button) findViewById(R.id.bt_home);

        bt_home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_home:
               startActivity(new Intent(HomeActivity.this,MainActivity.class));

                break;
        }
    }
    @Subscribe(threadMode =ASYNC)
    public void restory(PerSonBean perSonBean){
        Log.e("TAG", "restory: "+perSonBean.name+","+perSonBean.age );
    }
}