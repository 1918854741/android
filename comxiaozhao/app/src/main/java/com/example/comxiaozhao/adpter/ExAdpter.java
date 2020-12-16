package com.example.comxiaozhao.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.comxiaozhao.bean.ExBean;

import java.util.ArrayList;
import java.util.List;

public class ExAdpter extends BaseExpandableListAdapter {
    public ArrayList<ExBean.DataBean>list;
    private Context mContext;

    public ExAdpter(ArrayList<ExBean.DataBean> list, Context context) {
        this.list = list;
        mContext = context;
    }

    //父条目数量
    @Override
    public int getGroupCount() {
        return list.size();
    }
    //子条目数量
    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildren().size();
    }
    //父条目数据
    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition).getId();
    }
    //子条目数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(childPosition).getName();
    }
    //父条目id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    //子条目id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    //父条目数据
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setText(list.get(groupPosition).getId()+"");
        textView.setTextSize(15);
        return textView;
    }
    //字条目数据
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setText(list.get(childPosition).getName());
        return textView;
    }
    //子条目是否可执行操作 默认为false  需要该为true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void getdata(List<ExBean.DataBean> data) {
        if(data!=null&&data.size()>0){
            list.addAll(data);
            notifyDataSetChanged();
          }
    }
}
