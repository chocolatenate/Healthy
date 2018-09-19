package com.example.lab203_30.healthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BmiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        final ArrayList<String> _weight = new ArrayList<>();
//
//        final ArrayAdapter<String> weightAdapter = new ArrayAdapter<String>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,_weight
//        );
//        ListView _weightList = getView().findViewById(R.id.weight_list);
//        _weightList.setAdapter(weightAdapter);
        Button backBtn = getView().findViewById(R.id.bmi_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BMI","GOTO MENU");
                getActivity().startActivity(new Intent(getActivity(), MenuActivity.class));
            }
        });
        Button calBtn = getView().findViewById(R.id.cal_btn);
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText height = getView().findViewById(R.id.height);
                EditText weight = getView().findViewById(R.id.weight);
                String heightStr = height.getText().toString();
                String weightStr = weight.getText().toString();

                try {

                    float heightFlot = Float.parseFloat(heightStr);
                    float weightFlot = Float.parseFloat(weightStr);

                    float bmi = weightFlot / ((heightFlot / 100) * (heightFlot / 100));
                    if (TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(weightStr)) {
                        Toast.makeText(getContext(),"กรุณาระบุข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();
                        Log.d("BMI", "FIELD NAME IS EMPTY");
                    } else {
                        TextView you = getView().findViewById(R.id.your_bmi);
                        TextView bmiTextview = getView().findViewById(R.id.bmi);
                        you.setText("YOUR BMI");
                        bmiTextview.setText(String.format("%.2f", bmi));
                        //_weight.add(Float.toString(bmi));
                        Log.d("BMI","BMI IS VALUE"+String.format("%.2f", bmi));
                    }

                } catch (Exception e){

                }
            }
        });
    }
}
