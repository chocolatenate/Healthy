package com.example.lab203_30.healthy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WeightFormActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// saveInstanceState = ถ้าเกิดactivityถูกสร้างจากอันเก่าจะมี
        setContentView(R.layout.activity_weight_form);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.weight_form_view, new WeightFormFragment())
                    .addToBackStack(null)
                    .commit();
        }

    }
}
