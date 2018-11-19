package com.example.lab203_30.healthy.post;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.lab203_30.healthy.BmiFragment;
import com.example.lab203_30.healthy.R;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// saveInstanceState = ถ้าเกิดactivityถูกสร้างจากอันเก่าจะมี
        setContentView(R.layout.activity_comment);
        if (savedInstanceState == null) {

            int postId = getIntent().getIntExtra("postId", 0);

            Fragment fragment = new CommentFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("postid", postId);
                fragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.comment_view, fragment)
                    .addToBackStack(null)
                    .commit();
        }

    }
}
