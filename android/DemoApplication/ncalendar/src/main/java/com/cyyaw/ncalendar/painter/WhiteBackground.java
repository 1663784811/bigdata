package com.cyyaw.ncalendar.painter;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import org.joda.time.LocalDate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class WhiteBackground implements CalendarBackground {


    @Override
    public Drawable getBackgroundDrawable(LocalDate localDate, int totalDistance, int currentDistance) {
        return new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
                canvas.drawColor(Color.WHITE);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {

            }

            @SuppressLint("WrongConstant")
            @Override
            public int getOpacity() {
                return 0;
            }
        };
    }
}
