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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container,false);
        //สร้างviewาก ไฟล์ layout
    }

    @Override //ctrl+o
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser()!= null){
            getActivity().startActivity(new Intent(getActivity(),MenuActivity.class));
            return;//เพื่อไม่ให้ทำคำสังด้านล่าง
        }

        if (getView() == null) return;
        Button loginBtn = getView().findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userId = getView().findViewById(R.id.user_id);//รับview().ได้view จาก ID(R.id.ชื่อid)
                EditText passId = getView().findViewById(R.id.pass_id);
                String userIdStr = userId.getText().toString();
                String passIdStr = passId.getText().toString();
                if(TextUtils.isEmpty(userIdStr)||TextUtils.isEmpty(passIdStr)){
                    Toast.makeText(getContext(), "กรุญาระบุ user or password", Toast.LENGTH_SHORT).show();//Toast.makeText(getContex()/getActivity(),ข้อความ,เวลา)
                    Log.d("user","USER OR PASSWORD IS EMPTY");

//                }else if (!userIdStr.equals("admin")|| !passIdStr.equals("admin")){
//                    Toast.makeText(getContext(),"user or password ไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
//                    Log.d("user","INVALID USER OR PASSWORD");
                }else {
                    //getActivity().startActivity(new Intent(getActivity(), MenuActivity.class));//Intent -Actต้นทาง
//                    ((MainActivity) getActivity()).gotoBmi();
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(userIdStr,passIdStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult)
                        {
                            if(authResult.getUser().isEmailVerified()) {
                                getActivity().startActivity(new Intent(getActivity(), MenuActivity.class));
                                Log.d("user", "GOTO MENU");
                            } else {
                                Toast.makeText(getContext(),"EMAIL NOT VERIFIED",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("user","ERROR");
                            Toast.makeText(getContext(),"ERROE ="+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
        TextView regisBtn = getView().findViewById(R.id.regis_bnt);
        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),RegisterActivity.class));
            }
        });
        }
}
