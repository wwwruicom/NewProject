package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.newproject.R;
import com.bawei.newproject.Weixin;

import java.util.List;

public class MyBase extends BaseAdapter {
    List<Weixin.Result.AllList> lists;
    Context context;

    public MyBase(List<Weixin.Result.AllList> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=View.inflate(context, R.layout.item,null);
            viewHolder.title=convertView.findViewById(R.id.title);
            viewHolder.source=convertView.findViewById(R.id.source);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.title.setText(lists.get(position).getTitle());
        viewHolder.source.setText(lists.get(position).getSource());
        return convertView;
    }
    class ViewHolder{
        TextView title=null;
        TextView source=null;
    }
}
