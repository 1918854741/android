package com.example.a122demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt_main = (Button) findViewById(R.id.bt_main);

        bt_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_main:
//                EventBus.getDefault().post(new PerSonBean("张三",18));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new PerSonBean("张三",18));
                        startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    }
                }).start();
                break;
        }
    }
}