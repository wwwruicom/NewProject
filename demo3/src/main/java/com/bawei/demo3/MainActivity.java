package com.bawei.demo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText pwd;
    private EditText yhm;

    private String path="http://172.17.8.100/small/user/v1/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yhm = findViewById(R.id.dl_yhm);
        pwd = findViewById(R.id.dl_pwd);


    }

    public void Tozhu(View view) {

    }

    public void Tologin(View view) {

        final String name = this.yhm.getText().toString();
        final String pass = this.pwd.getText().toString();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url=new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    //传入地址参数
                    String params="phone"+ URLEncoder.encode(name,"UTF-8")+"&pwd="+URLEncoder.encode(pass,"UTF-8");

                    OutputStream outputStream = urlConnection.getOutputStream();
                    outputStream.write(params.getBytes());
                    outputStream.flush();
                    outputStream.close();

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = urlConnection.getInputStream();
                        int len=-1;
                        byte[] bytes=new byte[1024];
                        StringBuffer buffer=new StringBuffer();
                        while ((len=inputStream.read(bytes))!=-1){
                            String s=new String(bytes,0,len);
                            buffer.append(s);
                        }
                        String json = buffer.toString();
                        Log.i("TAG", json);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}
