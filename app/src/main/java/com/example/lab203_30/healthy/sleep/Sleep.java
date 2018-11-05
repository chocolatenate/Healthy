package com.example.lab203_30.healthy.sleep;

import android.content.ContentValues;

public class Sleep {
    String date;
    String time_start;
    String time_end;

    String diff;
    private int id;

    ContentValues row = new ContentValues();

    public Sleep(int id,String date , String time_start , String time_end,String diff){
        this.date = date;
        this.time_start = time_start;
        this.time_end = time_end;
        this.diff = diff;
        this.id = id;
    }

    public ContentValues getContent() {
        return row;
    }

    public void setContent(String date_sleep , String time_sleep , String time_wake,String diff) {
        this.row.put("date", date_sleep);
        this.row.put("sleep_time", time_sleep);
        this.row.put("wake_time", time_wake);
        this.row.put("diff", diff);

    }

    public Sleep() {


    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }


}
