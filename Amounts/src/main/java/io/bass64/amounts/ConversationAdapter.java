package io.bass64.amounts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.bass64.amounts.models.ConversationResult;

/**
 * Created by philostler on 17/10/13.
 */
public class ConversationAdapter<T> extends ArrayAdapter {
    public ConversationAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        ConversationResult result = (ConversationResult)getItem(position);
        TextView convertedValue = (TextView)view.findViewById(R.id.convertedValue);
        convertedValue.setText(String.valueOf(result.value));

        return view;
    }
}
