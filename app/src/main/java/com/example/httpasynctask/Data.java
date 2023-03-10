package com.example.httpasynctask;

public class Data {

    String date,time,count;

    public Data(String date, String time, String count) {
        this.date = date;
        this.time = time;
        this.count = count;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
