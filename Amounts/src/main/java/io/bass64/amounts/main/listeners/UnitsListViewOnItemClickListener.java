package io.bass64.amounts.main.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ViewFlipper;

import io.bass64.amounts.R;
import io.bass64.amounts.main.MainActivity;
import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Item click listener for Units List View in Main Activity
 * @author Phil Ostler - Bass64 Ltd
 */
public class UnitsListViewOnItemClickListener implements AdapterView.OnItemClickListener {
    public MainActivity activity;

    public UnitsListViewOnItemClickListener(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ViewFlipper groupsUnitsViewFlipper = (ViewFlipper)activity.findViewById(R.id.groups_units_view_flipper);
        groupsUnitsViewFlipper.setInAnimation(activity, R.anim.slide_in_from_left);
        groupsUnitsViewFlipper.setOutAnimation(activity, R.anim.slide_out_to_right);
        groupsUnitsViewFlipper.showPrevious();

        activity.getActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
