package com.bawei.demo2;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import adapter.MyBase;
import shu.UserDao;

public class MainActivity extends AppCompatActivity {

    String path="http://172.17.8.100/movieApi/movie/v1/findHotMovieList?page=1&count=9";
    private ListView listview;
    private UserDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listview);
        dao = new UserDao(this);
        //获取网络信息
        boolean connection = ConnectUtil.isConnection(this);

        if (connection){

            new Thread(){


                private List<Bean.Result> select;

                @Override
                public void run() {
                    super.run();
                    try {
                        URL url=new URL(path);
                        //建立连接
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        //设置请求方式
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(5000);
                        urlConnection.setConnectTimeout(5000);

                        int responseCode = urlConnection.getResponseCode();
                        if (responseCode == 200) {
                            InputStream inputStream = urlConnection.getInputStream();
                            int len=-1;
                            byte[] bytes=new byte[1024];
                            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                            while ((len=inputStream.read(bytes))!=-1){
                                byteArrayOutputStream.write(bytes,0,len);
                            }
                            String json = byteArrayOutputStream.toString();
                            Gson gson=new Gson();
                            Log.i("TAG", json);
                            Bean bean = gson.fromJson(json, Bean.class);
                            List<Bean.Result> list = bean.getResult();
                            Log.i("TAG", list.get(1).getName());


                            if (select == null) {
                                for (int i = 0; i < list.size(); i++) {
                                    String name = list.get(i).getName();
                                    String summary = list.get(i).getSummary();
                                    dao.insert(name,summary);
                                }
                            }

                            select = dao.select();
                            MyBase myBase=new MyBase(MainActivity.this,select);
                            listview.setAdapter(myBase);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }else {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("请打开网络");
            builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intent);
                }
            });
            builder.show();

        }

    }
}
