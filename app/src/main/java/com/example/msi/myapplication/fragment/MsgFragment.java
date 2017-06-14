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

import com.example.msi.myapplication.info.ItemInfo;
import com.example.msi.myapplication.activity.PopChatActivity;
import com.example.msi.myapplication.R;
import com.example.msi.myapplication.adapter.ChatListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MsgFragment extends Fragment {
    private ListView lv;
    private ChatListAdapter chatListAdapter;
    private String Name[]={"user1","user2","user3","user4"};
    private String counter[]={"hello","hi","你好", "再见"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_chat_list, null);
        lv = (ListView) view.findViewById(R.id.listview);
        chatListAdapter = new ChatListAdapter();
        chatListAdapter.setContext(getActivity());
        chatListAdapter.setmData(getList());
        lv.setAdapter(chatListAdapter);
        lv.setSelection(lv.getCount() - 1);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                ItemInfo people = (ItemInfo) chatListAdapter.getItem(position);
                Intent i = new Intent(getActivity() , PopChatActivity.class);
                startActivity(i);
            }
        });
        return  view;
    }
    public void onPause() {
        super.onPause();
    }
    private List<ItemInfo> getList() {
        List<ItemInfo> list = new ArrayList<ItemInfo>();
        for (int i = 0; i < 4; i++) {
            ItemInfo option = new ItemInfo();
            option.setName(Name[i]);
            option.setCounter(counter[i]);
            list.add(option);
        }
        return list;
    }
}
