package com.example.msi.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.msi.myapplication.R;

public class LabelActivity extends AppCompatActivity {
    private Button btnback;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylabel);
        btnback = (Button) findViewById(R.id.backbutton);
        btnback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent( LabelActivity.this , MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
