package io.bass64.amounts.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import io.bass64.amounts.AboutActivity;
import io.bass64.amounts.R;
import io.bass64.amounts.main.listeners.GroupsListViewOnItemClickListener;
import io.bass64.amounts.main.listeners.UnitsListViewOnItemClickListener;
import io.bass64.amounts.utils.FontUtils;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class MainActivity extends RoboActivity {
    @InjectView(R.id.groups_list_view) ListView groupsListView;
    @InjectView(R.id.units_list_view) ListView unitsListView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.action_about:
                openAbout();
                return true;
            default: return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView valueToConvert = (TextView)findViewById(R.id.valueToConvert);
        valueToConvert.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ViewFlipper flipper = (ViewFlipper)findViewById(R.id.groups_units_view_flipper);
                if(editable.toString().length() > 0) {
                    flipper.setVisibility(View.VISIBLE);
                } else {
                    flipper.setVisibility(View.INVISIBLE);
                }
            }
        });
        FontUtils.setFont(this, valueToConvert);

        // Groups List View
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.main_categories_titles));
        groupsListView.setAdapter(adapter);
        groupsListView.setOnItemClickListener(new GroupsListViewOnItemClickListener(this));

        // Units List View
        unitsListView.setOnItemClickListener(new UnitsListViewOnItemClickListener(this));
    }

    private void openAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
