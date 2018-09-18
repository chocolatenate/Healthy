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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterFragment extends Fragment {

    private FirebaseAuth mAuth;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register
                , container, false);
    }

   // private FirebaseAuth mAuth;
    private void sendVerifideEmail(FirebaseUser user){
        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("REGISTER","go2menu)");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        } );
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        Button regis = getView().findViewById(R.id.regis_bnt);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText email = getView().findViewById(R.id.regis_email);
                String email_str = email.getText().toString();
//                EditText name = getView().findViewById(R.id.regis_name);
//                String name_str = name.getText().toString();
                EditText pass = getView().findViewById(R.id.regis_pass);
                String pass_str = pass.getText().toString();
                EditText re_pass = getView().findViewById(R.id.regis_re_pass);
                String re_pass_str = re_pass.getText().toString();

                if(TextUtils.isEmpty(email_str) ||  TextUtils.isEmpty(pass_str)|| TextUtils.isEmpty(re_pass_str)){
                    Toast.makeText(getContext(),"กรุณาระบุข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER","FIELD NAME IS EMPTY");
                }else if(pass_str.length()<6 || re_pass_str.length()<6){
                    Toast.makeText(getContext(),"PASSWORDต้องมีความกว้างขั้นต่ำุ6ตัวอักษร",Toast.LENGTH_SHORT).show();

                }else if(!pass_str.equals(re_pass_str)){
                    Toast.makeText(getContext(),"PASSWORDไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                } else if(email_str.equals("admin")){
                    Toast.makeText(getContext(),"user นี้มีอยู่ในระบบแล้ว",Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER","USER ALREAD EXIST");

                }else if(pass_str.equals(re_pass_str)){
                    mAuth.createUserWithEmailAndPassword(email_str,pass_str).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("REGISTER","GO TO LOGIN");
                            sendVerifideEmail(authResult.getUser());
                            getActivity().finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("REGISTER","ERROR");
                            Toast.makeText(getActivity(),"ERROR="+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

//                    getActivity().startActivity(new Intent(getActivity(),BmiActivity.class));
//
//                    Log.d("REGISTER","GO TO BMI");

//                }else {
//                    Log.d("User","NOOOOOO");
              }
            }

        });
    }

}

