package com.example.lab203_30.healthy.sleep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lab203_30.healthy.BmiFragment;
import com.example.lab203_30.healthy.R;
import com.example.lab203_30.healthy.sleep.SleepFragment;

public class SleepActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// saveInstanceState = ถ้าเกิดactivityถูกสร้างจากอันเก่าจะมี
        setContentView(R.layout.activity_sleep);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.sleep_view, new SleepFragment())
                    .commit();
        }

    }
}
