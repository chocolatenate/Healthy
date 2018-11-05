package com.example.lab203_30.healthy.sleep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lab203_30.healthy.R;
import com.example.lab203_30.healthy.weight.WeightFormFragment;

public class SleepFormActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// saveInstanceState = ถ้าเกิดactivityถูกสร้างจากอันเก่าจะมี
        setContentView(R.layout.activity_sleep_form);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.sleep_form_view, new SleepFormFragment())
                    .addToBackStack(null)
                    .commit();
        }

    }
}
