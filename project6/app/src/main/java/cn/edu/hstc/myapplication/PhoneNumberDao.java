package cn.edu.hstc.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作phone_number表的DAO类
 */
public class PhoneNumberDao {
    private DBHelper dbHelper;
    public PhoneNumberDao(Context context){
        dbHelper=new DBHelper(context);
    }
    /**
     * 添加一条记录
     */
    public void add(PhoneNumber phoneNumber){
        //1.得到连接
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //2.执行insert
        ContentValues values=new ContentValues();
        values.put("number",phoneNumber.getNumber());
        long id=database.insert("phone_number",null,values);
        Log.i("TAG","id="+id);
        //设置id
        phoneNumber.setId((int)id);
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
        int deleteCount=database.delete("phone_number","_id=?",new String[]{id+""});
        Log.i("TAG","deleteCount="+deleteCount);
        //3.关闭
        database.close();
    }

    /**
     * 编辑一条记录
     */
    public void update(PhoneNumber phoneNumber){
        //1.得到连接
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //2.执行update
        ContentValues values=new ContentValues();
        values.put("number",phoneNumber.getNumber());
        int updateCount=database.update("phone_number",values,"_id="+phoneNumber.getId(),null);
        Log.i("TAG","updateCount="+updateCount);
        //3.关闭
        database.close();
    }

    /**
     * 查询所有记录封装成List<PhoneNumber>
     */
    public List<PhoneNumber> getAll() {
        List<PhoneNumber> list=new ArrayList<PhoneNumber>();
        //1.得到连接
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //2.执行query
        Cursor cursor=database.query("phone_number",null,null,null,null,null,"_id desc");
        //3.从cursor中取出所有数据并封装到List中
        while (cursor.moveToNext()){
            PhoneNumber phoneNumber=null;
            phoneNumber=new PhoneNumber();
            phoneNumber.setId(cursor.getInt(cursor.getColumnIndex("id")));
            phoneNumber.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            list.add(phoneNumber);
            //id
            //int id=cursor.getInt(0);
            //number
            //String number=cursor.getString(1);
            //PhoneNumber phoneNumber=new PhoneNumber(id,number);
            //list.add(new PhoneNumber(id,number));
        }
        //4.关闭
        cursor.close();
        database.close();

        return list;
    }
}
