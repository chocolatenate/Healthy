package com.example.lab203_30.healthy.Weight;

import android.app.Activity;
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

import com.example.lab203_30.healthy.R;
import com.example.lab203_30.healthy.WeightFormActivity;

import java.util.ArrayList;

public class WeightFragment extends Fragment {

    ArrayList<weight> weights = new ArrayList<>();
    private WeightAdapter mWeightAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        weights.add(new weight("01 Jan 2018", 63, "UP"));
        weights.add(new weight("02 Jan 2018", 64, "DOWN"));
        weights.add(new weight("03 Jan 2018", 63, "UP"));

//        final ArrayAdapter<weight> weightAdapter = new ArrayAdapter<weight>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,weights
//        );

        ListView weightList = getView().findViewById(R.id.weight_list);
        mWeightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weight_item,
                weights
        );
        weightList.setAdapter(mWeightAdapter);
        Button add = getView().findViewById(R.id.addweight_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), WeightFormActivity.class), 1234);

            }
        });

        //
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            String date = data.getStringExtra("date");
            String weight = data.getStringExtra("weight");

            weights.add(new weight(date, Integer.parseInt(weight), ""));
            mWeightAdapter.notifyDataSetChanged();


        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }
}
