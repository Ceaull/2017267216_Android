package cn.edu.hstc.memoapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库操作的帮助类
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context,"crud.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG","DBHelper onCreare()");
        //创建类
        db.execSQL("create table memo_app(_id integer primary key autoincrement, content varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
