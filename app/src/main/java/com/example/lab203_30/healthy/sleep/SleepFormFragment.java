package com.example.lab203_30.healthy.sleep;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab203_30.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SleepFormFragment extends Fragment {
    ArrayList<Sleep> sleeps = new ArrayList<>();

    private SQLiteDatabase database;

    public SleepFormFragment() {
        cv = new ContentValues();
    }

    private ContentValues cv;
    private SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form,container,false);
    }
    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        chkSelected();
        saveBtn();

        Button back = getView().findViewById(R.id.bmi_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("weight", "GOTO MENU");
                getActivity().startActivity(new Intent(getActivity(), SleepActivity.class));

            }
        });

    }

    void saveBtn(){
        Button save = getView().findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date = getView().findViewById(R.id.date);
                EditText time_start = getView().findViewById(R.id.sleep_form_start);
                EditText time_end = getView().findViewById(R.id.sleep_form_end);

                String date_str = date.getText().toString();
                String time_start_str = time_start.getText().toString();
                String time_end_str = time_end.getText().toString();

                String[] _sleepArray = time_start_str.split(":");
                String[] _wakeArray = time_end_str.split(":");

                String hour, minute;

                int intSleep = (Integer.parseInt(_sleepArray[0])) * 3600 + (Integer.parseInt(_sleepArray[1])) * 60;
                int intWake = (Integer.parseInt(_wakeArray[0]) * 3600) + (Integer.parseInt(_wakeArray[1]) * 60);

                if (intSleep > intWake) {
                    hour = String.valueOf(Math.round(86400 - (intSleep - intWake)) / 3600);
                    minute = String.valueOf(Math.round((86400 - (intSleep - intWake)) % 3600) / 60);

                } else {
                    hour = String.valueOf(Math.round(intWake - intSleep) / 3600);
                    minute = String.valueOf(Math.round((intWake - intSleep) % 3600) / 60);
                }
                if (hour.length() == 1){
                    hour = "0"+hour;
                }
                if (minute.length() == 1){
                    minute = "0"+minute;
                }

                String amountDiff = hour + ":" + minute;
                database = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

                database.execSQL(
                        "CREATE TABLE IF NOT EXISTS sleeps (_id INTEGER PRIMARY KEY AUTOINCREMENT, sleep_time VARCHAR(5), wake_time VARCHAR(5), date VARCHAR(11) , diff VARCHAR(5))"
                );

                Sleep item = new Sleep();


                item.setContent(date_str, time_start_str, time_end_str, amountDiff);

                cv = item.getContent();

                /// check if from selected

                sp = getContext().getSharedPreferences("selected_sleep", Context.MODE_PRIVATE);
                int id = sp.getInt("id", -1);

                if (id != -1) { //update from selected

                    database.update("sleeps", cv, "_id=" + id, null);
                    Log.d("SLEEP_FORM", "UPDATE ALREADY");

                    Toast.makeText(getActivity(), "Update Success"
                            , Toast.LENGTH_SHORT)
                            .show();

                    sp.edit().clear().commit();

                } else { // add new sleep

                    database.insert("sleeps", null, cv);
                    Log.d("SLEEP_FORM", "INSERT ALREADY");

                    Toast.makeText(getActivity(), "Save Success"
                            , Toast.LENGTH_SHORT)
                            .show();

                }

                getActivity().startActivity(new Intent(getActivity(), SleepActivity.class));

                Toast.makeText(getContext(),"บันทึกเรียบร้อย",Toast.LENGTH_SHORT).show();

            }
        });

    }

    void chkSelected() {


        sp = getContext().getSharedPreferences("selected_sleep", Context.MODE_PRIVATE);
        int id = sp.getInt("id", -1);
        String date = sp.getString("Date", null);


        if (id != -1) {
            EditText sleepTimeEdit = getView().findViewById(R.id.sleep_form_start);
            EditText wakeupTimeEdit = getView().findViewById(R.id.sleep_form_end);
            EditText dateEdit = getView().findViewById(R.id.date);

            sleepTimeEdit.setText(sp.getString("sleep_time", null));
            wakeupTimeEdit.setText(sp.getString("wake_time", null));
            dateEdit.setText(date);

        } else {
            return;
        }
    }


}

