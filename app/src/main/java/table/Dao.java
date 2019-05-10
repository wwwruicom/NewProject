package table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bawei.newproject.Weixin;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    SQLiteDatabase database;

    public Dao(Context context){
        SqliteHelper helper = new SqliteHelper(context);
        database = helper.getReadableDatabase();
    }

    public void insert(String title,String source){

        ContentValues values=new ContentValues();
        values.put("title",title);
        values.put("source",source);
        database.insert("users",null,values);

    }
    public List<Weixin.Result.AllList> select(){
        Cursor users = database.query("users", null, null, null, null, null, null);
        List<Weixin.Result.AllList> list=new ArrayList<>();
        while (users.moveToNext()){
            String title = users.getString(users.getColumnIndex("title"));
            String source = users.getString(users.getColumnIndex("source"));
            list.add(new Weixin.Result.AllList(title,source));
        }

        return list;
    }
    public void delete(String title,String source){
        database.delete("users","title = ? and source = ?",new String[]{title,source});
    }

    public void deletes(){
        database.delete("users",null,null);
    }
//    public void update(String title,String source,String tj){
//        ContentValues values=new ContentValues();
//        values.put("newtitle",title);
//        values.put("newsource",source);
//        database.update("users",values,"title = ?",new String[]{tj});
//    }

}
