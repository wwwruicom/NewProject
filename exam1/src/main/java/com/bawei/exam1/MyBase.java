package com.bawei.exam1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.exam1.sqlite.Bean;

import java.util.ArrayList;
import java.util.List;

public class MyBase extends BaseAdapter {

    List<Bean.Data.Forecast> list;
    Context context;

    public MyBase(List<Bean.Data.Forecast> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if (convertView == null) {
            convertView=View.inflate(context,R.layout.its,null);
            viewHolder.date=convertView.findViewById(R.id.date);
            viewHolder.high=convertView.findViewById(R.id.high);
            viewHolder.fengxiang=convertView.findViewById(R.id.fx);
            viewHolder.low=convertView.findViewById(R.id.low);
            viewHolder.type=convertView.findViewById(R.id.type);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.date.setText(list.get(position).getDate());
        viewHolder.high.setText(list.get(position).getHigh());
        viewHolder.fengxiang.setText(list.get(position).getFengxiang());
        viewHolder.low.setText(list.get(position).getLow());
        viewHolder.type.setText(list.get(position).getType());
        return convertView;
    }

    private static class ViewHolder {
        public TextView date;
        public TextView high;
        public TextView fengxiang;
        public TextView low;
        public TextView type;
    }
}
