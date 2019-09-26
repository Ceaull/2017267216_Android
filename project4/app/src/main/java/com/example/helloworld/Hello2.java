package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Hello2 extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Hello2";
    private static int objCount1=0;
    private int mobjCount1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello2);
        objCount1++;
        mobjCount1=objCount1;
        Log.d(TAG, "onCreate execute-"+mobjCount1);
        settupClicks1();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart-"+mobjCount1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume-"+mobjCount1);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume-"+mobjCount1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause-"+mobjCount1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop-"+mobjCount1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        objCount1--;
        Log.d(TAG, "onDestroy-"+mobjCount1);
    }

    @Override
    public void onClick(View v) {
        Button b;
        Intent intent;
        if(v.getId()==R.id.btToHello1){
            intent=new Intent(Hello2.this,Hello1.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.btToHello2){
            intent=new Intent(Hello2.this,Hello2.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.btToHello3){
            intent=new Intent(Hello2.this,Hello3.class);
            startActivity(intent);
        }

    }
    private void settupClicks1(){
        Button b;
        b=(Button)findViewById(R.id.btToHello1);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.btToHello2);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.btToHello3);
        b.setOnClickListener(this);
    }
}
