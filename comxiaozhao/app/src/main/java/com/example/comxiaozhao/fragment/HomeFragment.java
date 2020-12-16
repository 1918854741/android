package com.example.comxiaozhao.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.comxiaozhao.R;
import com.example.comxiaozhao.adpter.ExAdpter;
import com.example.comxiaozhao.bean.ExBean;
import com.example.comxiaozhao.net.AserviceAPI;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private ExAdpter mExAdpter;
    private ArrayList<ExBean.DataBean> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview(getView());
        initdata();
    }

    private void initdata() {
        new Retrofit.Builder()
                .baseUrl(AserviceAPI.ex_list)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(AserviceAPI.class)
                .getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<ExBean>() {
                    @Override
                    public void onNext(ExBean exBean) {
                        mExAdpter.getdata(exBean.getData());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initview(View view) {
        ExpandableListView ex_list=view.findViewById(R.id.ex_list);
        //创建数据源
        mList = new ArrayList<>();
        //创建适配器
        mExAdpter = new ExAdpter(mList, getActivity());
        ex_list.setAdapter(mExAdpter);
        //父条目吐丝
        ex_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getActivity(), mList.get(groupPosition).getId()+"", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //子条目吐丝
        ex_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getActivity(), mList.get(childPosition).getName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}