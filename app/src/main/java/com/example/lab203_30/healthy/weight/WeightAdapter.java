package com.example.lab203_30.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.lab203_30.healthy.R;

import java.util.List;
import java.util.Locale;

public class WeightAdapter extends ArrayAdapter<Weight> {

    private List<Weight> weights;
    private Context context;

    private LayoutInflater mLayoutInflater;//ใช้สร้าview

    public WeightAdapter(Context context, int resource, List<Weight> object) {
        super(context, resource, object);
        this.weights = object;
        this.context = context;

        mLayoutInflater = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View _weightItem;

        if (convertView == null) {
            _weightItem = mLayoutInflater.inflate(
                    R.layout.fragment_weight_item, parent, false);
        } else {
            _weightItem = convertView;
        }

        TextView _date = _weightItem.findViewById(R.id.weight_item_date);
        TextView _weight = _weightItem.findViewById(R.id.weight_item_weight);
        TextView status = _weightItem.findViewById(R.id.status);

        Weight _row = weights.get(position);
        _date.setText(_row.getDate());
        _weight.setText(String.format(Locale.getDefault(), "%d", _row.getWeight()));

        if (position > 0) {
            Weight _prevRow = weights.get(position - 1);
            if (_prevRow.weight > _row.weight) {
                status.setText("DOWN");
            } else if (_row.weight > _prevRow.weight) {
                status.setText("UP");
            }else{
                status.setText("");
            }
        }else {
            status.setText(" ");
        }

        return _weightItem;
    }
}

