package com.company.registration_system.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Entity
public class Customers implements Comparable<Customers>{

    @Id
    private int id;
    private String serial;
    private int status;
    private String specialist;
    private String time;
    private String date;
    private String start;
    private String end;

    @Transient
    private DateFormat f = new SimpleDateFormat("yyyy-MM-dd");

    public Customers() {
    }

    public Customers(int id, String serial, int status, String specialist, String time, String date, String start, String end) {
        this.id = id;
        this.serial = serial;
        this.status = status;
        this.specialist = specialist;
        this.time = time;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public int compareTo(Customers o) {
        try {
            return f.parse(this.getDate()).compareTo(f.parse(o.getDate()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
