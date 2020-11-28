package com.company.registration_system.Model;

public class Customer {

    private int userId;
    private int lineNr;
    private boolean status;
    private String specialist;
    private String time;

    public Customer() {
    }

    public Customer(int userId, int lineNr, boolean status, String specialist, String time) {
        this.userId = userId;
        this.lineNr = lineNr;
        this.status = status;
        this.specialist = specialist;
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLineNr() {
        return lineNr;
    }

    public void setLineNr(int lineNr) {
        this.lineNr = lineNr;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
}
