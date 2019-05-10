package com.bawei.demo2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 封装工具类
 */
public class ConnectUtil {

    public static boolean isConnection(Context context){
        //得到系统服务
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //得到网络
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }else {
            return false;
        }

    }

}
