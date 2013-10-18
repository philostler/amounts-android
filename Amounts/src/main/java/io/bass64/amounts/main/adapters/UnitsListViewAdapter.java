package io.bass64.amounts.main.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.bass64.amounts.R;
import io.bass64.amounts.models.ConversationResult;
import io.bass64.amounts.utils.FontUtils;

/**
 * Created by philostler on 17/10/13.
 */
public class UnitsListViewAdapter<T> extends ArrayAdapter {
    public UnitsListViewAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        ConversationResult result = (ConversationResult)getItem(position);

        TextView unitsLongName = (TextView)view.findViewById(R.id.units_long_name);
        FontUtils.setFont(getContext(), unitsLongName);
        unitsLongName.setText(String.valueOf(result.unit.title));

        TextView convertedValue = (TextView)view.findViewById(R.id.converted_value);
        FontUtils.setFont(getContext(), convertedValue);
        convertedValue.setText(String.valueOf(result.value));

        return view;
    }
}
