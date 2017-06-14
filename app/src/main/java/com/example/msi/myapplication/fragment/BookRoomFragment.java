package com.example.msi.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.msi.myapplication.adapter.MyExpandableListViewAdapter;
import com.example.msi.myapplication.activity.LabelActivity;
import com.example.msi.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BookRoomFragment extends Fragment {
    private Button btnlabel;
    private ExpandableListView listview;
    private  List<String> groupArray;
    private  List<List<String>> childArray;
    private  List<Integer> ivArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mybookroom, null);
        initialData();
        listview = (ExpandableListView) view.findViewById(R.id.expandablelistview);
        listview.setAdapter(new MyExpandableListViewAdapter(getActivity(),groupArray,childArray,ivArray ));
        listview.setGroupIndicator(null);

        btnlabel = (Button) view.findViewById(R.id.labelButton);
        btnlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LabelActivity.class);
                startActivity(i);
            }
        });
        return view;
    }


    private void initialData() {
        groupArray = new  ArrayList<String>();
        childArray = new  ArrayList<List<String>>();
        ivArray = new  ArrayList<Integer>();

        groupArray.add("我想要读的书" );
        groupArray.add("我正在读的书" );
        groupArray.add("我已读过的书" );
        groupArray.add("我的收藏" );

        ivArray.add(R.drawable.likesbook);
        ivArray.add(R.drawable.reading);
        ivArray.add(R.drawable.readplan);
        ivArray.add(R.drawable.collectionbook);

        List<String> tempArray = new  ArrayList<String>();
        tempArray.add("第一条" );
        tempArray.add("第二条" );
        tempArray.add("第三条" );

        for (int  index = 0 ; index <groupArray.size(); ++index)
        {
            childArray.add(tempArray);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
