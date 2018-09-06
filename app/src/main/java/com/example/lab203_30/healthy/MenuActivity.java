package com.example.lab203_30.healthy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// saveInstanceState = ถ้าเกิดactivityถูกสร้างจากอันเก่าจะมี
        setContentView(R.layout.activity_menu);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.menu_view, new MenuFragment())
                    .commit();
        }

    }


//    void gotoBmi(){
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.main_view, new BmiFragment())
//                .commit();
//    }

}
