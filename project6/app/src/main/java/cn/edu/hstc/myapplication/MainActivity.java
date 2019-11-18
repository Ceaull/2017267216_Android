package cn.edu.hstc.myapplication;

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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    //private ListView lv_main;
    private PhoneNumberAdapter adapter;
    private PhoneNumberDao dao;
    private List<PhoneNumber> data;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lv_main=getListView();
        adapter=new PhoneNumberAdapter();
        dao=new PhoneNumberDao(this);
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
        PhoneNumber phoneNumber=data.get(position);
        switch (item.getItemId()){
            case 1://更新
                showUpdateDialog(phoneNumber);
                break;
            case 2://删除
                //1.删除数据表对应的数据
                dao.deleteById(phoneNumber.getId());
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
     * @param phoneNumber
     */
    private void showUpdateDialog(final PhoneNumber phoneNumber){
        final EditText editText=new EditText(this);
        editText.setHint(phoneNumber.getNumber());
        new AlertDialog.Builder(this)
                .setTitle("更新黑名单")
                .setView(editText)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //1.保存数据表中
                        String newNumber=editText.getText().toString();
                        phoneNumber.setNumber(newNumber);
                        //2.更新数据表对应的数据
                        dao.update(phoneNumber);
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
        editText.setHint("输入电话号码");
        new AlertDialog.Builder(this)
                .setTitle("添加电话号码")
                .setView(editText)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //1.保存数据表中
                        String number=editText.getText().toString();
                        //PhoneNumber phoneNumber=new PhoneNumber();
                        PhoneNumber phoneNumber=null;
                        phoneNumber=new PhoneNumber();
                        phoneNumber.setId(-1);
                        phoneNumber.setNumber(number);
                        //list.add(phoneNumber);
                        dao.add(phoneNumber);
                        //2.保存数据到List
                        data.add(0,phoneNumber);
                        //3.通知更新列表
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }
    class PhoneNumberAdapter extends BaseAdapter {
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
            PhoneNumber phoneNumber=data.get(position);
            TextView textView=(TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(phoneNumber.getNumber());
            return convertView;
        }
    }
}
