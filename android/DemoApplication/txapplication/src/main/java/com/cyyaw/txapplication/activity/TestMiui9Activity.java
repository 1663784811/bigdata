package com.cyyaw.txapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cyyaw.ncalendar.calendar.BaseCalendar;
import com.cyyaw.ncalendar.calendar.Miui9Calendar;
import com.cyyaw.ncalendar.enumeration.CalendarState;
import com.cyyaw.ncalendar.enumeration.DateChangeBehavior;
import com.cyyaw.ncalendar.listener.OnCalendarChangedListener;
import com.cyyaw.ncalendar.listener.OnCalendarMultipleChangedListener;
import com.cyyaw.ncalendar.listener.OnCalendarScrollingListener;
import com.cyyaw.txapplication.R;
import com.cyyaw.txapplication.adapter.RecyclerViewAdapter;

import org.joda.time.LocalDate;

import java.util.List;


/**
 * Created by necer on 2018/11/7.
 */
public class TestMiui9Activity extends BaseActivity {


    private Miui9Calendar miui9Calendar;

    private TextView tv_result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miui9);

        tv_result = findViewById(R.id.tv_result);

        miui9Calendar = findViewById(R.id.miui9Calendar);

        miui9Calendar.setCalendarState(CalendarState.WEEK);
        miui9Calendar.setCheckMode(checkModel);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);


        miui9Calendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate, DateChangeBehavior dateChangeBehavior) {
                tv_result.setText(year + "年" + month + "月" + "   当前页面选中 " + localDate);
                Log.e(TAG, "baseCalendar::" + baseCalendar);
            }

        });
        miui9Calendar.setOnCalendarMultipleChangedListener(new OnCalendarMultipleChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, List<LocalDate> currPagerCheckedList, List<LocalDate> totalCheckedList, DateChangeBehavior dateChangeBehavior) {
                tv_result.setText(year + "年" + month + "月" + " 当前页面选中 " + currPagerCheckedList.size() + "个  总共选中" + totalCheckedList.size() + "个");
                Log.d(TAG, year + "年" + month + "月");
                Log.d(TAG, "当前页面选中：：" + currPagerCheckedList);
                Log.d(TAG, "全部选中：：" + totalCheckedList);

                Log.e(TAG, "baseCalendar::" + baseCalendar);
            }

        });

        miui9Calendar.setOnCalendarScrollingListener(new OnCalendarScrollingListener() {
            @Override
            public void onCalendarScrolling(float dy) {
                Log.d(TAG, "onCalendarScrolling：：" + dy);
            }
        });

    }

    public void jump_2018_08_11(View view) {
        miui9Calendar.jumpDate("2018-08-11");
    }

    public void jump_2019_06_20(View view) {
        miui9Calendar.jumpDate("1901-02-01");
    }

    public void jump_2020_08_11(View view) {
        miui9Calendar.jumpDate("2020-08-11");
    }

    public void lastPager(View view) {
        miui9Calendar.toLastPager();
    }

    public void nextPager(View view) {
        miui9Calendar.toNextPager();
    }

    public void today(View view) {
        miui9Calendar.toToday();
    }

    public void fold(View view) {
        if (miui9Calendar.getCalendarState() == CalendarState.WEEK) {
            miui9Calendar.toMonth();
        } else {
            miui9Calendar.toWeek();
        }
    }

}
