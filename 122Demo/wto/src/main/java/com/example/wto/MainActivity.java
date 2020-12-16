package com.example.wto;

import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wto.Persontrs.MainPer;
import com.example.wto.adpther.RvAdpther;
import com.example.wto.bean.BannerBean;
import com.example.wto.bean.FoodBean;
import com.example.wto.view.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView rev;
    private MainPer mainPer;
    private RvAdpther rvAdpther;
    private ArrayList<FoodBean.DataBean> list;
    private ArrayList<BannerBean.DataBean> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建P层对象
        mainPer = new MainPer(this);
        setContentView(R.layout.activity_main);
        initView();
        initdata();
    }

    private void initdata() {
        mainPer.getdata();
        mainPer.getdata2();
    }

    private void initView() {
        rev = (RecyclerView) findViewById(R.id.rev);
        rev.setLayoutManager(new LinearLayoutManager(this));
        //创建banner
        beans = new ArrayList<>();
        //创建集合
        list = new ArrayList<>();
        //创建适配器
        rvAdpther = new RvAdpther(beans, this.list, this);
        rev.setAdapter(rvAdpther);
    }

    @Override
    public void OnNext(Object o) {
        Log.e("TAG", "OnError: 网络成功");
        showToast("网络成功");
        if (o instanceof BannerBean) {
            BannerBean bannerBean = (BannerBean) o;
            rvAdpther.getdata(bannerBean.getData());
//       /     rvAdpther.getdata(new BannerBean().getData());
//            BannerBean bannerBean = (BannerBean) o;
//            rvAdpther.barlist.addAll(bannerBean.getData());
//            rvAdpther.notifyDataSetChanged();
        } else if (o instanceof FoodBean) {
            FoodBean foodBean = (FoodBean) o;
            rvAdpther.getlist(foodBean.getData());
//           FoodBean foodBean = (FoodBean) o;
//            rvAdpther.list.addAll(foodBean.getData());
//            rvAdpther.notifyDataSetChanged();
        }
//        rvAdpther.addData(o);

//

    }

    @Override
    public void OnError(String log) {
        Log.e("TAG", "OnError: 网络失败");
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
