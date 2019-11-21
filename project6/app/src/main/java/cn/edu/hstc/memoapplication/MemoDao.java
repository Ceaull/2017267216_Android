package cn.edu.hstc.memoapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作memo_app表的Dao类
 */
public class MemoDao {
    private DBHelper dbHelper;
    public MemoDao(Context context){
        dbHelper=new DBHelper(context);
    }

    /**
     * 添加一条记录
     */
    public void add(Memo memo){
        //1.得到连接
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //2.执行insert
        ContentValues values=new ContentValues();
        values.put("content" ,memo.getContent());
        long id=database.insert("memo_app",null,values);
        Log.i("TAG","id="+id);
        //设置id
        memo.setId((int)id);
        //3.关闭
        database.close();
    }

    /**
     * 根据id删除一条记录
     */
    public void deleteById(int id){
        //1.得到连接
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //2.执行delete
        int deleteCount=database.delete("memo_app","_id=?",new String[]{id+""});
        Log.i("TAG","deleteCount="+deleteCount);
        //3.关闭
        database.close();
    }

    /**
     * 编辑一条记录
     */
    public void update(Memo memo){
        //1.得到连接
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //2.执行update
        ContentValues values=new ContentValues();
        values.put("content",memo.getContent());
        int updateCount=database.update("memo_app",values,"_id="+memo.getId(),null);
        Log.i("TAG","updateCount="+updateCount);
        //3.关闭
        database.close();
    }

    /**
     * 查询所有记录封装成List<Memo>
     */
    public List<Memo> getAll() {
        List<Memo> list=new ArrayList<Memo>();
        //1.得到连接
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //2.执行query
        Cursor cursor=database.query("memo_app",null,null,null,null,null,"_id desc");
        //3.从cursor中取出所有数据并封装到List中
        while (cursor.moveToNext()){
            Memo memo=null;
            memo=new Memo();
            memo.setId(cursor.getInt(cursor.getColumnIndex("id")));
            memo.setContent(cursor.getString(cursor.getColumnIndex("content")));
            list.add(memo);
        }
        //4.关闭
        cursor.close();
        database.close();

        return list;
    }
}
