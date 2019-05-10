package com.bawei.exam2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 封装工具类
 */
public class MyUtil {

    public static boolean isConnection(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo!=null&&networkInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }
}
