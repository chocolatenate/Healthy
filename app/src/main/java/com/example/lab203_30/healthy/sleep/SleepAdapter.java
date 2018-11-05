package com.example.lab203_30.healthy.sleep;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab203_30.healthy.R;
import com.example.lab203_30.healthy.weight.Weight;

import java.util.List;
import java.util.Locale;

public class SleepAdapter extends ArrayAdapter<Sleep> {

    private List<Sleep> sleep_list;
    private Context context;

    private LayoutInflater mLayoutInflater;//ใช้สร้าview

    public SleepAdapter(Context context, int resource, List<Sleep> object) {
        super(context, resource, object);
        this.sleep_list = object;
        this.context = context;

        mLayoutInflater = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View sleep_Item;

        if (convertView == null) {
            sleep_Item = mLayoutInflater.inflate(
                    R.layout.fragment_sleep_item, parent, false);
        } else {
            sleep_Item = convertView;
        }

        TextView date = sleep_Item.findViewById(R.id.sleep_item_date);
        TextView start = sleep_Item.findViewById(R.id.sleep_item_start);
        TextView end = sleep_Item.findViewById(R.id.sleep_item_end);
        TextView cal = sleep_Item.findViewById(R.id.sleep_cal);

        Sleep _row = sleep_list.get(position);

        date.setText(_row.getDate());
        start.setText(_row.getTime_start());
        end.setText(_row.getTime_end());
        cal.setText(_row.getDiff());


        return sleep_Item;
    }
}

