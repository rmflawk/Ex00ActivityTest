package com.youngstudio.ex00activitytest;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class Page3ListAdapter extends BaseAdapter {

    ArrayList<String> datas;
    Context context;

    public Page3ListAdapter(ArrayList<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        //LayoutInflator를 소환하기
        LayoutInflater inflater= LayoutInflater.from(context);

        //1. new view
        if(view==null){
            view= inflater.inflate(R.layout.listview_item, viewGroup, false);
        }

        //2. bind view
        TextView tv= view.findViewById(R.id.tv);
        tv.setText( datas.get(position) );

        return view;
    }
}
