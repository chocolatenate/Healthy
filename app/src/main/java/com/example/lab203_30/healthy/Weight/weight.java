package com.example.lab203_30.healthy.Weight;

public class weight {
    String date;
    int weight;
    String status;

    public weight(){}
    public weight(String date, int weight, String status){
        this.date = date;
        this.weight = weight;
        this.status = status;
    }

    public int getWeight() {

        return weight;
    }

    public String getDate() {

        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
//
//import android.view.View;
//
//import java.util.Date;
//
//public class weight {
//    String date;
//    int weight;
//
//    public weight(String d,int w) {
//        date=d;
//        weight=w;
//    }
//    //fn+alt+insert
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public int getWeight() {
//        return weight;
//    }
//
//    public void setWeight(int weight) {
//        this.weight = weight;
//    }
//}

