package table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bawei.newproject.FragmentOne;

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users(id integer primary key autoincrement," +
                "title text," +
                "source text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
