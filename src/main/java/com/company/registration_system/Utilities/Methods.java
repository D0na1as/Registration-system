package com.company.registration_system.Utilities;

import com.company.registration_system.Model.Customer;
import com.company.registration_system.Model.Time;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Methods {

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    public static List<Time> filterTimes(List<Time> allTimes, List<String> occupTimes, String date) throws ParseException {
        for (String slot:occupTimes) {
            for (Time time:allTimes) {
                if(time.getTime().equals(slot)) {
                    allTimes.remove(time);
                    break;
                } else if (date.equals(Methods.getDate())) {

                    DateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date time1 = sdf.parse(time.getTime());
                    Date time2 = sdf.parse(Methods.getTime());

                    if ( time1.before(time2))  {
                        allTimes.remove(time);
                    }
                }
            }
        }
        return allTimes;
    }
    public static List<Customer> sortTime(List<Customer> list) {
        DateFormat f = new SimpleDateFormat("HH:mm");
        list.sort((o1, o2) -> {
            try {
                return f.parse(o1.getTime()).compareTo(f.parse(o2.getTime()));
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        });
        return list;
    }

    public static String timeLeft (String startDay, String startTime ) {

                // SimpleDateFormat converts the
                // string format to date object
                SimpleDateFormat sdf
                        = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm");
                String endDate = startDay + " " + startTime;
                String startDate = Methods.getDate() + " " + Methods.getTime();
                // Try Class
                try {

                    // parse method is used to parse
                    // the text from a string to
                    // produce the date
                    Date d1 = sdf.parse(startDate);
                    Date d2 = sdf.parse(endDate);

                    // Calucalte time difference
                    // in milliseconds
                    long difference_In_Time
                            = d2.getTime() - d1.getTime();

                    // Calucalte time difference in seconds,
                    // minutes, hours, years, and days
                    long difference_In_Seconds
                            = TimeUnit.MILLISECONDS
                            .toSeconds(difference_In_Time)
                            % 60;

                    long difference_In_Minutes
                            = TimeUnit
                            .MILLISECONDS
                            .toMinutes(difference_In_Time)
                            % 60;

                    long difference_In_Hours
                            = TimeUnit
                            .MILLISECONDS
                            .toHours(difference_In_Time)
                            % 24;

                    long difference_In_Days
                            = TimeUnit
                            .MILLISECONDS
                            .toDays(difference_In_Time)
                            % 365;

                    long difference_In_Years
                            = TimeUnit
                            .MILLISECONDS
                            .toDays(difference_In_Time)
                            / 365l;

                    String timeLeft = "";
                    if (difference_In_Time >0) {
                        if (difference_In_Years != 0) {
                            timeLeft += difference_In_Years + " year(s), ";
                        }
                        if (difference_In_Days != 0) {
                             timeLeft += difference_In_Days + " d, ";
                        }
                        if (difference_In_Hours > 10) {
                            timeLeft += "" + difference_In_Hours + "h, ";
                        }
                        if (difference_In_Hours > 10) {
                            timeLeft += "" + difference_In_Minutes + "min.";
                        }
                    } else {
                        timeLeft = "Your time passed";
                    }

                    return timeLeft;
                }
                catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
    }

}
