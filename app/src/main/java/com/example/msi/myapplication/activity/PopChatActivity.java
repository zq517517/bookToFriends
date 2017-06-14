package com.example.msi.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.msi.myapplication.info.ChatInfo;
import com.example.msi.myapplication.R;
import com.example.msi.myapplication.adapter.ChatAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PopChatActivity extends Activity implements View.OnClickListener {
    ListView lv;
    BaseAdapter ba;
    ArrayList al;
    EditText et;
    Button b,btnback;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_chat);
        al=new ArrayList();

        btnback=(Button) findViewById(R.id.btnback);

        ChatInfo ci=new ChatInfo("2012-11-7 17:35:12","hf","hello");
        al.add(ci);

        ci=new ChatInfo("2012-11-7 17:35:40","pa","hao are you?");
        al.add(ci);

        ci=new ChatInfo("2012-11-7 17:36:10","hf","fine,are you?");
        al.add(ci);

        ci=new ChatInfo("2012-11-7 17:36:10","pa","soso");
        al.add(ci);

        ci=new ChatInfo("2012-11-7 17:36:30","hf","ok...");
        al.add(ci);

        et=(EditText) findViewById(R.id.et);
        b=(Button) findViewById(R.id.btnfasong);
        b.setOnClickListener(this);
        btnback.setOnClickListener(this);





        lv=(ListView) findViewById(R.id.listView1);
        ba=new ChatAdapter(this,al);



        lv.setAdapter(ba);
        lv.setSelection(lv.getCount()-1);




    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnfasong)
        {
            InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            String str=et.getEditableText().toString();
            ChatInfo cii=new ChatInfo(getCurrentTime(),"pa",str);
            al.add(cii);
            System.out.println("----------------------->>>>"+str);
            ba.notifyDataSetChanged();
            et.setText("");
            lv.setSelection(lv.getCount()-1);

        }
        if(v.getId()==R.id.btnback)
        {
            this.finish();
        }
    }

    String getCurrentTime()
    {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());

    }
}
