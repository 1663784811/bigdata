package com.cyyaw.txapplication.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.cyyaw.ncalendar.calendar.Miui10Calendar;
import com.cyyaw.ncalendar.enumeration.CheckModel;
import com.cyyaw.txapplication.R;
import com.cyyaw.txapplication.painter.LigaturePainter;
import com.cyyaw.txapplication.painter.TicketPainter;

import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;


public class CustomCalendarActivity extends AppCompatActivity {

    Miui10Calendar miui10Calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        miui10Calendar = findViewById(R.id.miui10Calendar);
        miui10Calendar.setCheckMode(CheckModel.MULTIPLE);
        LigaturePainter painter = new LigaturePainter(this);
        miui10Calendar.setCalendarPainter(painter);

    }

    public void ligaturePainter(View view) {
        LigaturePainter painter = new LigaturePainter(this);
        miui10Calendar.setCalendarPainter(painter);
    }

    public void ticketPainter(View view) {
        TicketPainter ticketPainter = new TicketPainter(this, miui10Calendar);

        Map<LocalDate, String> priceMap = new HashMap<>();
        priceMap.put(new LocalDate("2019-06-07"), "￥350");
        priceMap.put(new LocalDate("2019-07-07"), "￥350");
        priceMap.put(new LocalDate("2019-06-30"), "￥350");
        priceMap.put(new LocalDate("2019-07-03"), "￥350");
        priceMap.put(new LocalDate("2019-07-04"), "￥350");
        priceMap.put(new LocalDate("2019-07-10"), "￥350");
        priceMap.put(new LocalDate("2019-07-15"), "￥350");
        priceMap.put(new LocalDate("2019-07-30"), "￥350");
        priceMap.put(new LocalDate("2019-08-04"), "￥350");
        priceMap.put(new LocalDate("2019-08-29"), "￥350");

        ticketPainter.setPriceMap(priceMap);

        miui10Calendar.setCalendarPainter(ticketPainter);
    }
}
