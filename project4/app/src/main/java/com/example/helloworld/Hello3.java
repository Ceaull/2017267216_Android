package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Hello3 extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Hello3";
    private static int objCount=0;
    private int mobjCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello3);
        objCount++;
        mobjCount=objCount;
        Log.d(TAG, "onCreate execute-"+mobjCount);
        settupClicks2();
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
        Intent intent;
        if(v.getId()==R.id.btToHello1){
            intent=new Intent(Hello3.this,Hello1.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.btToHello2){
            intent=new Intent(Hello3.this,Hello2.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.btToHello3){
            intent=new Intent(Hello3.this,Hello3.class);
            startActivity(intent);
        }
    }
    private void settupClicks2(){
        Button b;
        b=(Button)findViewById(R.id.btToHello1);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.btToHello2);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.btToHello3);
        b.setOnClickListener(this);
    }
}
