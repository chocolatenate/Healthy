package com.example.lab203_30.healthy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// saveInstanceState = ถ้าเกิดactivityถูกสร้างจากอันเก่าจะมี
        setContentView(R.layout.activity_register);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.regis_view, new RegisterFragment())
                    .commit();
        }

    }
}
