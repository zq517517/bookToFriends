package com.example.msi.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msi.myapplication.R;

import java.util.List;

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<String> groupArray;
    private List<Integer> ivArray;
    private List<List<String>> childArray;
    private Context mContext;

    public MyExpandableListViewAdapter(Context context, List<String> groupArray, List<List<String>> childArray,List<Integer> ivArray){
        mContext = context;
        this.groupArray = groupArray;
        this.childArray = childArray;
        this.ivArray = ivArray;
    }

    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        GroupHolder holder = null;
        if(view == null){
            holder = new GroupHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.expandlist_group, null);
            holder.groupName = (TextView)view.findViewById(R.id.tv_group_name);
            holder.arrow = (ImageView)view.findViewById(R.id.iv_arrow);
            holder.item = (ImageView)view.findViewById(R.id.iv_item);
            view.setTag(holder);
        }else{
            holder = (GroupHolder)view.getTag();
        }

        //判断是否已经打开列表
        if(isExpanded){
            holder.arrow.setBackgroundResource(R.drawable.down_arrow);
        }else{
            holder.arrow.setBackgroundResource(R.drawable.right_arrow);
        }

        holder.groupName.setText(groupArray.get(groupPosition));
        holder.item.setImageResource(ivArray.get(groupPosition));

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder holder = null;
        if(view == null){
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.expandlist_item, null);
            holder.childName = (TextView)view.findViewById(R.id.tv_child_name);
            holder.divider = (ImageView)view.findViewById(R.id.iv_divider);
            view.setTag(holder);
        }else{
            holder = (ChildHolder)view.getTag();
        }

        if(childPosition == 0){
            holder.divider.setVisibility(View.GONE);
        }

        holder.childName.setText(childArray.get(groupPosition).get(childPosition));
        holder.childName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点到了内置的textview",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder{
        public TextView groupName;
        public ImageView arrow;
        public ImageView item;
    }

    class ChildHolder{
        public TextView childName;
        public ImageView divider;
    }
}
