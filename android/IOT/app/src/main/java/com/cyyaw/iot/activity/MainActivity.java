package com.cyyaw.iot.activity;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cyyaw.iot.R;
import com.cyyaw.iot.activity.adapter.MyPagerAdapter;
import com.cyyaw.iot.activity.entity.TabEntity;
import com.cyyaw.iot.activity.fragment.HomeFragment;
import com.cyyaw.iot.activity.fragment.MyFragment;
import com.cyyaw.iot.activity.fragment.NewsFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页", "消息", "我的"};
    private int[] mIconUnselectIds = {R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect, R.mipmap.tab_contact_unselect};
    private int[] mIconSelectIds = {R.mipmap.tab_home_select, R.mipmap.tab_speech_select, R.mipmap.tab_contact_select};

    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;

    // =======================================================
    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initData() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(NewsFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.setOffscreenPageLimit(mFragments.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitles, mFragments));
    }




    private void simulateClick() {
        // 创建一个新的线程执行模拟点击操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // 创建 Instrumentation 实例
                        Instrumentation inst = new Instrumentation();

                        // 模拟触摸事件，ACTION_DOWN表示按下
                        MotionEvent downEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 200, 200, 0);
                        inst.sendPointerSync(downEvent);

                        // 延迟一段时间，模拟用户操作的持续时间
                        SystemClock.sleep(100);

                        // 模拟触摸事件，ACTION_UP表示抬起
                        MotionEvent upEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 200, 200, 0);
                        inst.sendPointerSync(upEvent);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}