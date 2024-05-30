package com.cyyaw.txapplication;

import android.os.Bundle;


import org.joda.time.LocalDate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cyyaw.ncalendar.calendar.BaseCalendar;
import com.cyyaw.ncalendar.calendar.MonthCalendar;
import com.cyyaw.ncalendar.enumeration.DateChangeBehavior;
import com.cyyaw.ncalendar.listener.OnCalendarChangedListener;

/**
 * Created by necer on 2020/3/24.
 */
public class TestActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);


       MonthCalendar monthCalendar = findViewById(R.id.monthCalendar);
       monthCalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
           @Override
           public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate, DateChangeBehavior dateChangeBehavior) {

           }
       });

    }
}
