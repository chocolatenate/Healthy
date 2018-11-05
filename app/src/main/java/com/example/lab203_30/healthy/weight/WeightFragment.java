package com.example.lab203_30.healthy.weight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab203_30.healthy.MenuActivity;
import com.example.lab203_30.healthy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WeightFragment extends Fragment {

    ArrayList<Weight> weights = new ArrayList<>();
    private WeightAdapter mWeightAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button back = getView().findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), MenuActivity.class));
            }
        });

//        weights.add(new Weight("01 Jan 2018", 63));
//        weights.add(new Weight("02 Jan 2018", 64));
//        weights.add(new Weight("03 Jan 2018", 63));
;

        final ListView weightList = getView().findViewById(R.id.weight_list);
        mWeightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weight_item,
                weights
        );
        weightList.setAdapter(mWeightAdapter);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //get value form firebase
        FirebaseFirestore.getInstance().collection("myfitness").document(uid)
                .collection("Weight").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                weights.clear();

                for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                    weights.add(d.toObject(Weight.class));
                }

                mWeightAdapter.notifyDataSetChanged();
            }
        });//get value form firebase


//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                documentSnapshot.getData(); // map
//                Weight w = documentSnapshot.toObject(Weight.class); // object
//                // w.
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//            }
//        });
//        weights.add(w);

        Button add = getView().findViewById(R.id.addweight_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), WeightFormActivity.class));

            }
        });

        //
    }
// ดึงข้อมูลโดยใช้ RESULT_OK
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
//            String date = data.getStringExtra("date");
//            String weight = data.getStringExtra("Weight");
//
//            weights.add(new Weight(date, Integer.parseInt(weight)));
//            mWeightAdapter.notifyDataSetChanged();
//
//
//        }
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
}
