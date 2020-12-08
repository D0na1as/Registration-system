package com.company.registration_system.Utilities;

import com.company.registration_system.Model.Customer;
import com.company.registration_system.Model.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static String genSerial() {
        Random r = new Random();
        int result = 100000 + (int)(r.nextFloat() * 900000);
        return String.valueOf(result);
    }

    public static List<Time> filterTimes(List<Time> allTimes, List<String> occupTimes) {
        for (String slot:occupTimes) {
            for (Time time:allTimes) {
                if(time.getTime().equals(slot)) {
                    allTimes.remove(time);
                    break;
                }
            }
        }
        return allTimes;
    }
    public static List<Customer> sortTime(List<Customer> list) {
        DateFormat f = new SimpleDateFormat("hh:mm");
        list.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                try {
                    return f.parse(o1.getTime()).compareTo(f.parse(o2.getTime()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        return list;
    }

}
