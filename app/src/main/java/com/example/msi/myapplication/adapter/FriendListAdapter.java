package com.example.msi.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.msi.myapplication.info.ItemInfo;
import com.example.msi.myapplication.R;

import java.util.List;

public class FriendListAdapter extends BaseAdapter {
    private List<ItemInfo> mData;
    private Context context;

    public void setmData(List mData) {
        this.mData = mData;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        LayoutInflater li=LayoutInflater.from(context);
        view=li.inflate(R.layout.friend_item, null);
        ItemInfo list=mData.get(position);
        ((TextView)view.findViewById(R.id.username)).setText(list.getName());

        return view;

    }
}
