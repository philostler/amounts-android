package io.bass64.amounts;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by philostler on 17/10/13.
 */
public class ConversationAdapter<T> extends ArrayAdapter {
    public ConversationAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
