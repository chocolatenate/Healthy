package com.example.lab203_30.healthy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lab203_30.healthy.weight.WeightFragment;

public class WeightActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// saveInstanceState = ถ้าเกิดactivityถูกสร้างจากอันเก่าจะมี
        setContentView(R.layout.activity_weight);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.weight_view, new WeightFragment())
                    .commit();
        }

    }
}
