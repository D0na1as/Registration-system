package com.company.registration_system.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Methods {

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");
        Date time = new Date();
        return dateFormat.format(time);
    }

    public static String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date time = new Date();
        return dateFormat.format(time);
    }

    public static List<String> getDatesList (int dateCount) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        ArrayList<String> daysList = new ArrayList<>();
        daysList.add(date.format(c.getTime()));
        for (int i = 0; i < dateCount; i++) {
            c.add(Calendar.DATE, 1);
            daysList.add(date.format(c.getTime()));
        }
        return daysList;
    }
}
