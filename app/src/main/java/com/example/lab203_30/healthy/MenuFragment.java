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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab203_30.healthy.post.PostActivity;
import com.example.lab203_30.healthy.sleep.SleepActivity;
import com.example.lab203_30.healthy.weight.WeightActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MenuFragment extends Fragment {
    ArrayList<String> _menu = new ArrayList<>();

    //
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("MENU sleep");
        _menu.add("Post");
        _menu.add("Setup");
        _menu.add("SignOut");


        final ArrayAdapter<String> _menuAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1, _menu
        );

        ListView _menuList = getView().findViewById(R.id.menu_list);
        _menuList.setAdapter(_menuAdapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MENU", "Click on menu = " + _menu.get(i));
                if (i == 0) {
                    getActivity().startActivity(new Intent(getActivity(), BmiActivity.class));

                } else if (i == 1) {
                    getActivity().startActivity(new Intent(getActivity(), WeightActivity.class));
                }else if(i==2){
                    getActivity().startActivity(new Intent(getActivity(), SleepActivity.class));
                }else if (i==3){
                    getActivity().startActivity(new Intent(getActivity(),PostActivity.class));
                } else if (i==5){
                    getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                    FirebaseAuth.getInstance().signOut();
                }

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
