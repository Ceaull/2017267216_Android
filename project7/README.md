# 多线程编程-在一定秒数内自动累加的程序

## 运行结果显示

1. 开始界面

![images](https://github.com/Ceaull/2017267216_android/tree/master/project7/images/1.PNG)

2. 点击开始增加按钮后,每一秒累加次数加一，十秒后程序自动暂停

![images](https://github.com/Ceaull/2017267216_android/tree/master/project7/images/2.PNG)

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
        <Button
            android:id="@+id/start_download"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="开始下载" />

        <Button
            android:id="@+id/pause_download"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="暂停下载" />

        <Button
            android:id="@+id/cancel_download"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="取消下载" />
    </LinearLayout>
</androidx.constraintlayout.widget.ConstraintLayout>
```
2、MainActivity.java
```
package cn.edu.hstc.pro7;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int UPDATE_TEXT = 1;
    private TextView text;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        Button counter = findViewById(R.id.button);
        counter.setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressLint("SetTextI18n")
        public void handleMessage(Message message) {
            if (message.what == UPDATE_TEXT) {
                count++;
                text.setText("当前累加得次数为：" + count);
            }
        }
    };
    
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            handler.sendMessage(message);
                        }
                    };
                    timer.schedule(task, 0, 1000);
                    try {
                        Thread.sleep(10000);
                        timer.cancel();
                    } catch (InterruptedException ignored) {
                    }
                }
            }).start();
        }
    }
}

```
