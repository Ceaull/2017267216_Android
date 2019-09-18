package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hello2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello2);
        settupClicks1();
    }

    @Override
    public void onClick(View v) {
        Button b;
        Intent intent;
        if(v.getId()==R.id.btToHello1){
            intent=new Intent(Hello2.this,Hello1.class);
            startActivity(intent);
        }

    }
    private void settupClicks1(){
        Button b;
        b=(Button)findViewById(R.id.btToHello1);
        b.setOnClickListener(this);
    }
}
