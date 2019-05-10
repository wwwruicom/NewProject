package com.bawei.exam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connection = MyUtil.isConnection(this);

    }

    /**
     * 点击按钮判断网络
     * @param view
     */
    public void pan(View view) {
        
        if (connection){
            Toast.makeText(this, "“有网络连接”", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "没有网络连接", Toast.LENGTH_SHORT).show();
        }
    }
}
