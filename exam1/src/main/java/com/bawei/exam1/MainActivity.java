package com.bawei.exam1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.bawei.exam1.sqlite.Bean;
import com.bawei.exam1.sqlite.UserDao;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String path="https://www.apiopen.top/weatherApi?city=%E5%8C%97%E4%BA%AC";
    private ListView lv;
    private UserDao dao;
    private List<Bean.Data.Forecast> select;
    private List<Bean.Data.Forecast> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);

        boolean connect = ConnectUtil.isConnect(this);
        if (connect){

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        URL url=new URL(path);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.setReadTimeout(5000);
                        urlConnection.setConnectTimeout(5000);
                        int responseCode = urlConnection.getResponseCode();
                        if (responseCode==200){
                            InputStream inputStream=urlConnection.getInputStream();
                            int len=-1;
                            byte[] bytes=new byte[1024];
                            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                            while ((len=inputStream.read(bytes))!=-1){
                                byteArrayOutputStream.write(bytes,0,len);
                            }
                            String json = byteArrayOutputStream.toString();
                            Log.i("TAG", json);
                            Gson gson=new Gson();
                            Bean bean = gson.fromJson(json, Bean.class);
                            list = bean.getData().getForecast();
                            Log.i("TAG", list.get(1).getDate());
                            dao = new UserDao(MainActivity.this);

                            if (select==null){
                                for (int i = 0; i < list.size(); i++) {
                                    String date = list.get(i).getDate();
                                    String high = list.get(i).getHigh();
                                    String fengxiang = list.get(i).getFengxiang();
                                    String low = list.get(i).getLow();
                                    String type = list.get(i).getType();
                                    dao.insert(date,high,fengxiang,low,type);
                                }
                            }
                            select = dao.select();
                            MyBase myBase=new MyBase(select,MainActivity.this);
                            lv.setAdapter(myBase);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }else{
            final AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("打开网络连接");
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
