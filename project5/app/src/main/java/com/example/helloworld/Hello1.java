package com.example.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Hello1 extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Hello1";
    private static int objCount=0;
    private int mobjCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world_layout);
        objCount++;
        mobjCount=objCount;
        Log.d(TAG, "onCreate execute-"+mobjCount);
        settupClicks();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart-"+mobjCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume-"+mobjCount);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume-"+mobjCount);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause-"+mobjCount);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop-"+mobjCount);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        objCount--;
        Log.d(TAG, "onDestroy-"+mobjCount);
    }

    @Override
    public void onClick(View v) {
        Button b;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Intent intent1 = new Intent(Intent.ACTION_DIAL);
        if(v.getId()==R.id.btToHello1){
            //intent=new Intent(Hello1.this,Hello1.class);
            //Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://www.baidu.com"));
            startActivity(intent);
        }
        if(v.getId()==R.id.btToHello2){
            //intent=new Intent(Hello1.this,Hello2.class);
            intent1.setData(Uri.parse("tel:10086"));
            startActivity(intent1);
        }
        if(v.getId()==R.id.btToHello3){
            //intent=new Intent(Hello1.this,Hello3.class);
            intent.setData(Uri.parse("geo:"));
            startActivity(intent);
        }
    }
    private void settupClicks(){
        Button b;
        b=(Button)findViewById(R.id.btToHello1);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.btToHello2);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.btToHello3);
        b.setOnClickListener(this);
    }
}
