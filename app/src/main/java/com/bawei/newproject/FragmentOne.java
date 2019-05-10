package com.bawei.newproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import adapter.MyBase;
import table.Dao;
import table.SqliteHelper;

public class FragmentOne extends Fragment {
    Dao dao;
    private List<Weixin.Result.AllList> allLists;
    private ListView listview;
    private List<Weixin.Result.AllList> select;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragone, container, false);
        listview = view.findViewById(R.id.listview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        File files=new File(Environment.getExternalStorageDirectory()+"/weixin.txt");
        try {
            InputStream inputStream=new FileInputStream(files);
            int len=-1;
            byte[] bytes=new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            while ((len=inputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,len);
            }
            String json = byteArrayOutputStream.toString();
            Gson gson=new Gson();
            Log.i("TAG", json);
            Weixin weixin=gson.fromJson(json,Weixin.class);
            Log.i("TAG", String.valueOf(weixin));
            allLists = weixin.getResult().getList();
            dao=new Dao(getActivity());

            select = dao.select();
            if (select.size()==0){
                for (int i = 0; i < allLists.size(); i++) {
                    String title = allLists.get(i).getTitle();
                    String source = allLists.get(i).getSource();
                    dao.insert(title,source);
                }
                select = dao.select();
            }

            final MyBase myBase=new MyBase(select,getActivity());
            listview.setAdapter(myBase);

            listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    dao.delete(select.get(position).getTitle(),select.get(position).getSource());
                    select.remove(select.remove(position));
                    myBase.notifyDataSetChanged();
                    return true;
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        listview.setOnItemLongClickListener(null);
        dao.deletes();

    }
}
