# 实现增删查改

## 运行结果显示

1. 点击运行后开始页面

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.1.PNG)

2. 添加页面

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.2.PNG)

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.3.PNG)

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.4.PNG)

3.左键长按内容，会出现删除和更新
  
![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.5.PNG)

4.更新页面

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.6.PNG)

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.7.PNG)

5.删除页面
  
![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.8.PNG)

## 代码显示

1.界面设计，activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <ListView
            android:id="@android:id/list"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1" >

        </ListView>

        <TextView
            android:id="@android:id/empty"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:text="空白"
            android:gravity="center"/>

        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:onClick="add"
            android:text="添加备忘录" />
    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```
2.实体类，Memo.java
```
package cn.edu.hstc.memoapplication;

/**
 * memo_app表的实体类
 */
public class Memo {
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}

```

3.数据库操作的帮助类，DBHelper.java
```
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
```

4.memo_app的Dao类，MemoDao.java
```
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
```

5.实现功能，MainActivity.java
```
package cn.edu.hstc.memoapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ListActivity {
    private MemoAdapter adapter;
    private MemoDao dao;
    private List<Memo> data;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter=new MemoAdapter();
        dao=new MemoDao(this);
        data=dao.getAll();
        //lv_main.setAdapter(adapter);
        setListAdapter(adapter);
        //给listView设置创建contextMenu的监听
        getListView().setOnCreateContextMenuListener(this);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //添加2个item
        menu.add(0,1,0,"更新");
        menu.add(0,2,0,"删除");
        //得到长按的position
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;
        position=info.position;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //得到对应PhoneNumber对象
        Memo memo=data.get(position);
        switch (item.getItemId()){
            case 1://更新
                showUpdateDialog(memo);
                break;
            case 2://删除
                //1.删除数据表对应的数据
                dao.deleteById(memo.getId());
                //2.删除List对应的数据
                data.remove(position);
                //3.通知更新列表
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * 显示更新的Dialog
     * @param memo
     */
    private void showUpdateDialog(final Memo memo){
        final EditText editText=new EditText(this);
        editText.setHint(memo.getContent());
        new AlertDialog.Builder(this)
                .setTitle("更新内容")
                .setView(editText)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //1.保存数据表中
                        String newNumber=editText.getText().toString();
                        memo.setContent(newNumber);
                        //2.更新数据表对应的数据
                        dao.update(memo);
                        //3.通知更新列表
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }

    public void add(View v){
        //1.显示添加的dialog（带输入框）
        final EditText editText=new EditText(this);
        editText.setHint("输入内容");
        new AlertDialog.Builder(this)
                .setTitle("添加内容")
                .setView(editText)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //1.保存数据表中
                        String content=editText.getText().toString();
                        //PhoneNumber phoneNumber=new PhoneNumber();
                        Memo memo=null;
                        memo=new Memo();
                        memo.setId(-1);
                        memo.setContent(content);
                        //list.add(phoneNumber);
                        dao.add(memo);
                        //2.保存数据到List
                        data.add(0,memo);
                        //3.通知更新列表
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }
    class MemoAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null) {
                convertView = View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null);
            }
            Memo memo=data.get(position);
            TextView textView=(TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(memo.getContent());
            return convertView;
        }
    }
}
```


