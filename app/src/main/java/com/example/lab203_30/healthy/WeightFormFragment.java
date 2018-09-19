package com.example.lab203_30.healthy;

import android.content.Intent;
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

import com.example.lab203_30.healthy.weight.Weight;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WeightFormFragment extends Fragment {
    ArrayList<Weight> weights = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form,container,false);
    }
    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button save = getView().findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date = getView().findViewById(R.id.date);
                EditText weight = getView().findViewById(R.id.weight);
                String date_str = date.getText().toString();
                String weight_str = weight.getText().toString();

                //weights.add(new Weight(date_str, Integer.parseInt(weight_str), "UP"));

                Intent data = new Intent();
                data.putExtra("Weight", weight_str);
                data.putExtra("date",date_str);
                Weight save = new Weight(date_str, Integer.parseInt(weight_str));

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseFirestore.getInstance().collection("myfitness").document(uid)
                        .collection("Weight").document(date_str).set(save).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("weight", "done");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("weight", "ERROR = "+e.getMessage());
                        Toast.makeText(getContext(),"ERROR = "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


//                getActivity().setResult(Activity.RESULT_OK, data);
//                getActivity().finish();
//
//                Toast.makeText(getContext(),"บันทึกเรียบร้อย",Toast.LENGTH_SHORT).show();

            }
        });
        Button back = getView().findViewById(R.id.bmi_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("weight", "GOTO MENU");
                getActivity().startActivity(new Intent(getActivity(), WeightActivity.class));

            }
        });

    }


}

