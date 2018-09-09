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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button regis = getView().findViewById(R.id.regis_bnt);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id = getView().findViewById(R.id.regis_id);
                String id_str = id.getText().toString();
                EditText name = getView().findViewById(R.id.regis_name);
                String name_str = name.getText().toString();
                EditText age = getView().findViewById(R.id.regis_age);
                String age_str = age.getText().toString();
                EditText pass = getView().findViewById(R.id.regis_pass);
                String pass_str = pass.getText().toString();
                if(TextUtils.isEmpty(id_str) || TextUtils.isEmpty(name_str) || TextUtils.isEmpty(age_str)|| TextUtils.isEmpty(pass_str)){
                    Toast.makeText(getContext(),"กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER","FIELD NAME IS EMPTY");
                }else if(id_str.equals("admin")){
                    Toast.makeText(getContext(),"user นี้มีอยู่ในระบบแล้ว",Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER","USER ALREAD EXIST");
                }else{
                    getActivity().startActivity(new Intent(getActivity(),BmiActivity.class));
                    Log.d("REGISTER","GO TO BMI");
                }
            }
        });
    }
}
