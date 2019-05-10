package com.bawei.exam1.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final SQLiteDatabase database;

    public UserDao(Context context) {
        SqliteHelper helper=new SqliteHelper(context);
        database = helper.getReadableDatabase();
    }
    public void insert(String date,String high,String fengxiang,String low,String type){
        ContentValues values=new ContentValues();
        values.put("date",date);
        values.put("high",high);
        values.put("fengxiang",fengxiang);
        values.put("low",low);
        values.put("type",type);
        database.insert("exam1",null,values);
    }
    public List<Bean.Data.Forecast> select(){
        Cursor cursor = database.query("exam1", null, null, null, null, null, null);
        List<Bean.Data.Forecast> list=new ArrayList<>();
        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String high = cursor.getString(cursor.getColumnIndex("high"));
            String fengxiang = cursor.getString(cursor.getColumnIndex("fengxiang"));
            String low = cursor.getString(cursor.getColumnIndex("low"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            list.add(new Bean.Data.Forecast(date,high,fengxiang,low,type));
        }
        return list;
    }
}
