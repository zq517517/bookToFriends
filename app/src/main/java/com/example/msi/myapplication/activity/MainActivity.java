package com.example.msi.myapplication.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msi.myapplication.R;
import com.example.msi.myapplication.adapter.FragmentAdapter;
import com.example.msi.myapplication.fragment.FriendListFragment;
import com.example.msi.myapplication.fragment.ContactFragment;
import com.example.msi.myapplication.fragment.BookRoomFragment;
import com.example.msi.myapplication.fragment.MsgFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;
    private ViewPager pager;
    private List<Fragment> fragments;
    private List<String> titles;

    private long firstTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_pager);

        tabHost = (FragmentTabHost) findViewById(R.id.tab_host);
        pager = (ViewPager) findViewById(R.id.pager);
        initTabHost();
        initPager();
        bindTabAndPager();
    }
    //双击退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void bindTabAndPager() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int position = tabHost.getCurrentTab();
                pager.setCurrentItem(position,true);
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 页面滑动 触发
             * @param position 当前显得第一个页面的索引，当滑动出现时屏幕就会显示两个pager， 向右滑 position为当前-1（左边的pager就显示出来了），向左滑position为当前（右面就显出来了），
             * @param positionOffset 0-1之间 position的偏移量 从原始位置的偏移量
             * @param positionOffsetPixels 从position偏移的像素值 从原始位置的便宜像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /**
             * 页面选中后
             * @param position 当前页面的index
             */
            @Override
            public void onPageSelected(int position) {
                tabHost.setCurrentTab(position);
            }

            /**
             * 页面滑动状态改变时触发
             * @param state 当前滑动状态 共三个状态值
             */
            @Override
            public void onPageScrollStateChanged(int state) {

                String stateStr="";
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        stateStr="正在拖动";
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        stateStr="正在去往最终位置 即将到达最终位置";
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        stateStr="滑动停止，当前页面充满屏幕";
                        break;
                }
            }

        });
    }

    private void initPager() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        fragments.add(new BookRoomFragment());
        fragments.add(new FriendListFragment());
        fragments.add(new MsgFragment());
        fragments.add(new ContactFragment());
        titles.add("书房");
        titles.add("好友");
        titles.add("消息");
        titles.add("我的");
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),fragments,titles);
        pager.setAdapter(adapter);
    }

    private void initTabHost() {
        tabHost.setup(this,getSupportFragmentManager(), R.id.pager);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.addTab(tabHost.newTabSpec("bookroom").setIndicator(createView(R.drawable.bookroom,"书房")), BookRoomFragment.class,null);
        tabHost.addTab(tabHost.newTabSpec("friendlist").setIndicator(createView(R.drawable.friendlist,"好友")), FriendListFragment.class,null);
        tabHost.addTab(tabHost.newTabSpec("message").setIndicator(createView(R.drawable.chat,"消息")), MsgFragment.class,null);
        tabHost.addTab(tabHost.newTabSpec("info").setIndicator(createView(R.drawable.accountbox,"我的")), ContactFragment.class,null);
    }

    private View createView(int icon,String tab){
        View view = getLayoutInflater().inflate(R.layout.fragment_tab_discover,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView title = (TextView) view.findViewById(R.id.title);
        imageView.setImageResource(icon);
        title.setText(tab);
        return  view;
    }
}
