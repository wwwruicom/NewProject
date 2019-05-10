package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.demo2.Bean;
import com.bawei.demo2.R;

import java.util.ArrayList;
import java.util.List;

public class MyBase extends BaseAdapter {

    Context context;
    List<Bean.Result> list=new ArrayList<>();

    public MyBase(Context context, List<Bean.Result> list) {
        this.context = context;
        this.list = list;
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
            convertView=View.inflate(context, R.layout.its,null);
            viewHolder.name=convertView.findViewById(R.id.name);
            viewHolder.summary=convertView.findViewById(R.id.summary);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
            viewHolder.name.setText(list.get(position).getName());
            viewHolder.summary.setText(list.get(position).getSummary());
        return convertView;
    }

    private static class ViewHolder {
        public TextView name;
        public TextView summary;
    }
}
