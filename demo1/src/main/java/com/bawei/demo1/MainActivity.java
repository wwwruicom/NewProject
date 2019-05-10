package com.bawei.demo1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    String path="http://pic29.nipic.com/20130601/12122227_123051482000_2.jpg";
    private ImageView imgview;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            imgview.setImageBitmap((Bitmap) msg.obj);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgview = findViewById(R.id.imgview);

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
                    if (responseCode == 200) {
                        InputStream inputStream = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        Message message=new Message();
                        message.obj=bitmap;
                        handler.sendMessage(message);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
}
