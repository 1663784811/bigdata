package com.cyyaw.ncalendar.adapter;

import android.content.Context;

import com.cyyaw.ncalendar.calendar.BaseCalendar;
import com.cyyaw.ncalendar.enumeration.CalendarType;

import org.joda.time.LocalDate;


public class MonthPagerAdapter extends BasePagerAdapter {

    public MonthPagerAdapter(Context context, BaseCalendar baseCalendar) {
        super(context, baseCalendar);
    }

    @Override
    protected LocalDate getPageInitializeDate(int position) {
        return getInitializeDate().plusMonths(position - getPageCurrIndex());
    }

    @Override
    protected CalendarType getCalendarType() {
        return CalendarType.MONTH;
    }
}
