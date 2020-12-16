package com.example.a1216demo;

import android.os.Bundle;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1216demo.factory.Schudefactory;
import com.example.a1216demo.factory.SchuderThreadPool;

import java.util.concurrent.TimeUnit;

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
                Schudefactory.getFactory(Schudefactory.schdule_threadPoll).executeTimerTask(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "我爱吃饭", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                },0,1000, TimeUnit.SECONDS);
                break;
        }
    }
}