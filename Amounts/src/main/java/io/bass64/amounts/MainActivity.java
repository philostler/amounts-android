package io.bass64.amounts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import io.bass64.amounts.models.ConversationResult;
import io.bass64.amounts.models.IConversation;
import io.bass64.amounts.utils.FontUtils;

public class MainActivity extends Activity {
    private IConversation conversation;

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
                ViewFlipper flipper = (ViewFlipper)findViewById(R.id.ccFlipper);
                if(editable.toString().length() > 0) {
                    flipper.setVisibility(View.VISIBLE);
                } else {
                    flipper.setVisibility(View.INVISIBLE);
                }
            }
        });
        FontUtils.setFont(this, valueToConvert);

        setupCategories();
        setupConversions();
    }

    private void setupCategories() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                                                        android.R.layout.simple_list_item_1,
                                                        getResources().getStringArray(R.array.main_categories_titles));
        ListView listView = (ListView)findViewById(R.id.categories);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String selectedItem = ((TextView)view).getText().toString();
                    Class klass = Class.forName("io.bass64.amounts.models." + selectedItem + "Conversation");
                    conversation = (IConversation)klass.newInstance();

                    TextView valueToConvert = (TextView)findViewById(R.id.valueToConvert);
                    ArrayList<ConversationResult> result = conversation.generate(Double.valueOf(valueToConvert.getText().toString()), null);

                    ConversationAdapter adapter = new ConversationAdapter<ConversationResult>(getApplicationContext(),
                                                                                              R.layout.list_view_item, R.id.unitOfMeasure,
                                                                                              result);
                    ListView conversions = (ListView)findViewById(R.id.conversions);
                    conversions.setAdapter(adapter);

                    getActionBar().setDisplayHomeAsUpEnabled(true);

                    ViewFlipper flipper = (ViewFlipper)findViewById(R.id.ccFlipper);
                    flipper.setInAnimation(getApplicationContext(), R.anim.slide_in_from_right);
                    flipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_to_left);
                    flipper.showNext();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setupConversions() {

        ListView listView = (ListView)findViewById(R.id.conversions);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewFlipper flipper = (ViewFlipper)findViewById(R.id.ccFlipper);
                flipper.setInAnimation(getApplicationContext(), R.anim.slide_in_from_left);
                flipper.setOutAnimation(getApplicationContext(), R.anim.slide_out_to_right);
                flipper.showPrevious();

                getActionBar().setDisplayHomeAsUpEnabled(false);
            }
        });
    }

    private void openAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
