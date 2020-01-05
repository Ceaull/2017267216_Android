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
