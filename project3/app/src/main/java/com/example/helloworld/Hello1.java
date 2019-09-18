package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Hello1 extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Hello1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world_layout);
        Log.d(TAG, "onCreate execute");
        settupClicks();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        Button b;
        Intent intent;
        if(v.getId()==R.id.btToHello1){
            intent=new Intent(Hello1.this,Hello2.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.btToHello2){

        }
    }
    private void settupClicks(){
        Button b;
        b=(Button)findViewById(R.id.btToHello1);
        b.setOnClickListener(this);
        b=(Button)findViewById(R.id.btToHello2);
        b.setOnClickListener(this);

    }
}
