package shu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.demo2.Bean;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private SQLiteDatabase database;

    public UserDao(Context context){
        SqliteHelper sqliteHelper=new SqliteHelper(context);
        database = sqliteHelper.getReadableDatabase();
    }

    public void insert(String name,String summary){
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("summary",summary);
        database.insert("demo2",null,values);
    }

    public List<Bean.Result> select(){
        Cursor cursor = database.query("demo2", null, null, null, null, null, null);
        List<Bean.Result> list=new ArrayList<>();
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String summary = cursor.getString(cursor.getColumnIndex("summary"));
            list.add(new Bean.Result(name,summary));
        }
        return list;
    }
}
