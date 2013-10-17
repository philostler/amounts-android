package io.bass64.amounts.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import io.bass64.amounts.R;

/**
 * Created by philostler on 16/10/13.
 */
public class FontUtils {
    public static void setFont(Context context, TextView view) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Light.ttf");
        view.setTypeface(typeface);
    }
}
