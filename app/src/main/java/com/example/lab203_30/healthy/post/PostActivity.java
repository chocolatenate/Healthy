package com.example.lab203_30.healthy.post;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lab203_30.healthy.R;

public class PostActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// saveInstanceState = ถ้าเกิดactivityถูกสร้างจากอันเก่าจะมี
        setContentView(R.layout.activity_post);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.post_view, new PostFragment())
                    .addToBackStack(null)
                    .commit();
        }

    }
}
