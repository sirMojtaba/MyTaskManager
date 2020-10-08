package com.example.task.utils;

import java.util.Date;

public class DateTime {
    public static String getDate(Date date){
        return android.text.format.DateFormat.format("MM/dd/yyyy", date.getTime()).toString();
    }

    public static String getTime(Date date){
        return android.text.format.DateFormat.format("HH:mm", date.getTime()).toString();
    }
}
