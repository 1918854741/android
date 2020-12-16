package com.example.wto.adpther;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.wto.R;
import com.example.wto.bean.BannerBean;
import com.example.wto.bean.FoodBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class RvAdpther extends RecyclerView.Adapter {
    public ArrayList<BannerBean.DataBean> barlist;
    public ArrayList<FoodBean.DataBean> list;
    private Context context;

    public RvAdpther(ArrayList<BannerBean.DataBean> barlist, ArrayList<FoodBean.DataBean> list, Context context) {
        this.barlist = barlist;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View bar = LayoutInflater.from(context).inflate(R.layout.bar_lay, parent, false);
            return new ViewHolderbar(bar);
        }
        View item = LayoutInflater.from(context).inflate(R.layout.item_lay, parent, false);
        return new ViewHolderitem(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            ViewHolderbar viewHolderbar = (ViewHolderbar) holder;
            viewHolderbar.banner.setImages(barlist).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerBean.DataBean dataBean = (BannerBean.DataBean) path;
                    Glide.with(context).load(dataBean.getImagePath()).load(imageView);
                }
            }).start();
        }
        int mpos = 0;
        if (getItemViewType(position) == 2) {
            if (barlist != null && barlist.size() > 0) {
                mpos = position - 1;
            }
            ViewHolderitem viewHolderitem = (ViewHolderitem) holder;
            Glide.with(context).load(list.get(mpos).getPic()).apply(new RequestOptions().placeholder(R.drawable.no_banner).circleCrop()).load(viewHolderitem.image);
            viewHolderitem.tv_text.setText(list.get(mpos).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if (barlist.size() == 0) {
            return list.size();
        }
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        return 2;
    }

    public void getdata(List<BannerBean.DataBean> data) {
        if(data!=null&&data.size()>0){
            barlist.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void getlist(List<FoodBean.DataBean> data) {
        if(data!=null&&data.size()>0){
            list.addAll(data);
            notifyDataSetChanged();
        }
    }

    public static
    class ViewHolderbar extends RecyclerView.ViewHolder {
        public View rootView;
        public Banner banner;

        public ViewHolderbar(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.banner = (Banner) rootView.findViewById(R.id.banner);
        }
    }

    public static
    class ViewHolderitem extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView image;
        public TextView tv_text;

        public ViewHolderitem(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.image = (ImageView) rootView.findViewById(R.id.image);
            this.tv_text = (TextView) rootView.findViewById(R.id.tv_text);
        }

    }
}
