package com.example.msi.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.msi.myapplication.activity.FriendInfoActivity;
import com.example.msi.myapplication.info.ItemInfo;
import com.example.msi.myapplication.R;
import com.example.msi.myapplication.adapter.FriendListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendListFragment extends Fragment {
    private ListView lv;
    private FriendListAdapter friendListAdapter;
    private String Name[]={"user1","user2","user3"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friendlist, null);
        lv=(ListView) view.findViewById(R.id.listview);
        friendListAdapter =new FriendListAdapter();
        friendListAdapter.setContext(getActivity());
        friendListAdapter.setmData(getList());
        lv.setAdapter(friendListAdapter);
        lv.setSelection(lv.getCount()-1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                ItemInfo people = (ItemInfo) friendListAdapter.getItem(position);
                Intent i = new Intent(getActivity() , FriendInfoActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    private List<ItemInfo> getList() {
        List<ItemInfo> list = new ArrayList<ItemInfo>();
        for (int i = 0; i < 3; i++) {
            ItemInfo friendlist = new ItemInfo();
            friendlist.setName(Name[i]);
            list.add(friendlist);
        }
        return list;
    }
}
