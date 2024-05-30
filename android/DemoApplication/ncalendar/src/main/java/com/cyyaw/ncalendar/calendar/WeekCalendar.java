package com.cyyaw.ncalendar.calendar;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cyyaw.ncalendar.adapter.BasePagerAdapter;
import com.cyyaw.ncalendar.adapter.WeekPagerAdapter;
import com.cyyaw.ncalendar.utils.CalendarUtil;

import org.joda.time.LocalDate;


public class WeekCalendar extends BaseCalendar {

    public WeekCalendar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected BasePagerAdapter getPagerAdapter(Context context, BaseCalendar baseCalendar) {
        return new WeekPagerAdapter(context, baseCalendar);
    }

    @Override
    protected int getTwoDateCount(LocalDate startDate, LocalDate endDate, int type) {
        return CalendarUtil.getIntervalWeek(startDate, endDate, type);
    }

    @Override
    protected LocalDate getIntervalDate(LocalDate localDate, int count) {
        return localDate.plusWeeks(count);
    }

}
