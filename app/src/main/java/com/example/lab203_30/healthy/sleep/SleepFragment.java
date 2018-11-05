package com.example.lab203_30.healthy.sleep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab203_30.healthy.MenuActivity;
import com.example.lab203_30.healthy.R;


import java.util.ArrayList;

public class SleepFragment extends Fragment {

    ArrayList<Sleep> sleep_list = new ArrayList<>();
    private SleepAdapter mSleepAdapter;

    private SQLiteDatabase database;
//    private Cursor db_query;

    private ListView listView;
    int position;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button back = getView().findViewById(R.id.back2menu_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), MenuActivity.class));
            }
        });


         listView = getView().findViewById(R.id.sleep_list);
        mSleepAdapter = new SleepAdapter(getActivity(), R.layout.fragment_sleep_item, sleep_list);

        //open to use db
        database = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

        //create table if not exist
        database.execSQL(
                "CREATE TABLE IF NOT EXISTS sleeps (_id INTEGER PRIMARY KEY AUTOINCREMENT, sleep_time VARCHAR(5), wake_time VARCHAR(5), date VARCHAR(11) , diff VARCHAR(5))"
        );

        String date,time_sleep,time_wake,time_diff;
        //query data
        Cursor db_query = database.rawQuery("SELECT * FROM sleeps", null);

        mSleepAdapter.clear();

        while(db_query.moveToNext()) {
            int id = db_query.getInt(0);
            date = db_query.getString(3);
            time_sleep = db_query.getString(1);
            time_wake = db_query.getString(2);
            time_diff = db_query.getString(4);

            sleep_list.add(new Sleep(id,date, time_sleep, time_wake,time_diff));
        }

        listView.setAdapter(mSleepAdapter);
        mSleepAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Sleep sleep = new Sleep();

                sleep = (Sleep) parent.getItemAtPosition(position);
                String cDate = sleep.getDate();
                String cSleep = sleep.getTime_start();
                String cWake = sleep.getTime_end();
                int _id = sleep.getId();


                SharedPreferences.Editor sp = getContext().getSharedPreferences("selected_sleep",Context.MODE_PRIVATE).edit();
                sp.putString("Date",cDate).apply();
                sp.putString("sleep_time",cSleep).apply();
                sp.putString("wake_time",cWake).apply();
                sp.putInt("id",_id);
                sp.commit();

                getActivity().startActivity(new Intent(getActivity(), SleepFormActivity.class));

            }
        });

        db_query.close();
        database.close();

        Button add = getView().findViewById(R.id.addsleep_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), SleepFormActivity.class));

            }
        });

   }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }
}
