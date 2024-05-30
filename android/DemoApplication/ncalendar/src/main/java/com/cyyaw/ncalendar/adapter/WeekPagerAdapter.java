package com.cyyaw.ncalendar.adapter;

import android.content.Context;

import com.cyyaw.ncalendar.calendar.BaseCalendar;
import com.cyyaw.ncalendar.enumeration.CalendarType;

import org.joda.time.LocalDate;


public class WeekPagerAdapter extends BasePagerAdapter {


    public WeekPagerAdapter(Context context, BaseCalendar baseCalendar) {
        super(context, baseCalendar);
    }

    @Override
    protected LocalDate getPageInitializeDate(int position) {
        return getInitializeDate().plusDays((position - getPageCurrIndex()) * 7);
    }

    @Override
    protected CalendarType getCalendarType() {
        return CalendarType.WEEK;
    }

}
