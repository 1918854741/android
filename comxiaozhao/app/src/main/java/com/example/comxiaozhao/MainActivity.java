package com.example.comxiaozhao;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.comxiaozhao.fragment.CollectionFragment;
import com.example.comxiaozhao.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout flay;
    private TabLayout tab;
    private RelativeLayout rel;
    private NavigationView nv;
    private DrawerLayout dr_main;
    private ImageView mHead;
    private HomeFragment mHomeFragment;
    private CollectionFragment mCollectionFragment;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initdata();
        //点击监听
        initListener();
        initfragment();
    }

    private void initfragment() {
        tab.addTab(tab.newTab().setText("首页").setIcon(R.drawable.shape1));
        tab.addTab(tab.newTab().setText("收藏").setIcon(R.drawable.shape2));
        mHomeFragment = new HomeFragment();
        mCollectionFragment = new CollectionFragment();
        mManager = getSupportFragmentManager();
        mManager.beginTransaction()
                .add(R.id.flay, mHomeFragment)
                .add(R.id.flay, mCollectionFragment)
                .show(mHomeFragment)
                .hide(mCollectionFragment)
                .commit();
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                 switch(tab.getPosition()){
                    case 0:
                        mManager.beginTransaction()
                                .show(mHomeFragment)
                                .hide(mCollectionFragment)
                                .commit();
                    break;
                     case 1:
                         mManager.beginTransaction()
                                 .show(mCollectionFragment)
                                 .hide(mHomeFragment)
                                 .commit();
                         break;
                 }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initListener() {
        //点击头部  换相册
        View headerView = nv.getHeaderView(0);
        mHead = headerView.findViewById(R.id.head_image);
        mHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101&&data!=null){
            Uri data1 = data.getData();
            mHead.setImageURI(data1);
        }
    }

    private void initdata() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dr_main, toolbar, 0, 0);
         toggle.syncState();
         dr_main.addDrawerListener(toggle);
         dr_main.addDrawerListener(new DrawerLayout.DrawerListener() {
             @Override
             public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                 rel.setX(drawerView.getWidth()*slideOffset);
             }

             @Override
             public void onDrawerOpened(@NonNull View drawerView) {

             }

             @Override
             public void onDrawerClosed(@NonNull View drawerView) {

             }

             @Override
             public void onDrawerStateChanged(int newState) {

             }
         });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        flay = (FrameLayout) findViewById(R.id.flay);
        tab = (TabLayout) findViewById(R.id.tab);
        rel = (RelativeLayout) findViewById(R.id.rel);
        nv = (NavigationView) findViewById(R.id.nv);
        dr_main = (DrawerLayout) findViewById(R.id.dr_main);
        //使侧滑图标正常显示
        nv.setItemIconTintList(null);

    }
}