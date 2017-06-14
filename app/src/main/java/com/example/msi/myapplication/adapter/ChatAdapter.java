package com.example.msi.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.msi.myapplication.info.ChatInfo;
import com.example.msi.myapplication.R;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    ArrayList al;
    Context c;
    public ChatAdapter(Context c, ArrayList al)
    {
        this.al=al;
        this.c=c;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return (ChatInfo)al.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v=null;
        LayoutInflater li=LayoutInflater.from(c);



        if(((ChatInfo)al.get(position)).getName().equals("pa"))
        {
            v=li.inflate(R.layout.layout_right, null);
            ((TextView)v.findViewById(R.id.tvdateandtime)).setText(((ChatInfo)al.get(position)).getTime());
            ((TextView)v.findViewById(R.id.tvname)).setText(((ChatInfo)al.get(position)).getName());
            ((TextView)v.findViewById(R.id.tvcontent)).setText(((ChatInfo)al.get(position)).getContent());
            return v;
        }
        else
        {
            v=li.inflate(R.layout.layout_left, null);
            ((TextView)v.findViewById(R.id.tvdateandtime)).setText(((ChatInfo)al.get(position)).getTime());
            ((TextView)v.findViewById(R.id.tvname)).setText(((ChatInfo)al.get(position)).getName());
            ((TextView)v.findViewById(R.id.tvcontent)).setText(((ChatInfo)al.get(position)).getContent());
            return v;
        }






    }
}
