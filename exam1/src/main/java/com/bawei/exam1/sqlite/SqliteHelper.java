package com.bawei.exam1.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(Context context) {
        super(context, "Exam1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table exam1(id integer primary key autoincrement," +
                "date text," +
                "high text," +
                "fengxiang text," +
                "low text," +
                "type text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
