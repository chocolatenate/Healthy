package com.example.lab203_30.healthy.weight;

public class Weight {
    String date;
    int weight;

    public Weight(){}
    public Weight(String date, int weight){
        this.date = date;
        this.weight = weight;
    }

    public int getWeight() {

        return weight;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
//public class Weight {
//    String date;
//    int Weight;
//
//    public Weight(String d,int w) {
//        date=d;
//        Weight=w;
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
//        return Weight;
//    }
//
//    public void setWeight(int Weight) {
//        this.Weight = Weight;
//    }
//}

