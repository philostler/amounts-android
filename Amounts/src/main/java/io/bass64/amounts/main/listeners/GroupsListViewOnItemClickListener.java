package io.bass64.amounts.main.listeners;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.inject.Inject;

import java.util.ArrayList;

import io.bass64.amounts.main.adapters.UnitsListViewAdapter;
import io.bass64.amounts.R;
import io.bass64.amounts.main.MainActivity;
import io.bass64.amounts.models.ConversationResult;
import io.bass64.amounts.models.conversation.group.ConversationGroupModel;
import io.bass64.amounts.models.conversation.group.IConversationGroup;
import roboguice.RoboGuice;

/**
 * Item click listener for Groups ListView in Main Activity
 * @author Phil Ostler - Bass64 Ltd
 */
public class GroupsListViewOnItemClickListener implements AdapterView.OnItemClickListener {
    public MainActivity activity;

    @Inject ConversationGroupModel model;

    public GroupsListViewOnItemClickListener(MainActivity activity) {
        this.activity = activity;

        RoboGuice.getInjector(activity).injectMembersWithoutViews(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String type = ((TextView)view).getText().toString();
            model.conversationGroup = instantiateConversationGroup(type);

            TextView valueToConvert = (TextView)activity.findViewById(R.id.valueToConvert);
            ArrayList<ConversationResult> result = model.conversationGroup.generate(Double.valueOf(valueToConvert.getText().toString()), null);

            UnitsListViewAdapter adapter = new UnitsListViewAdapter<ConversationResult>(activity,
                                                                                      R.layout.units_list_view_item, R.id.units_long_name,
                                                                                      result);
            ListView unitsListView = (ListView)activity.findViewById(R.id.units_list_view);
            unitsListView.setAdapter(adapter);

            activity.getActionBar().setDisplayHomeAsUpEnabled(true);

            ViewFlipper groupsUnitsViewFlipper = (ViewFlipper)activity.findViewById(R.id.groups_units_view_flipper);
            groupsUnitsViewFlipper.setInAnimation(activity, R.anim.slide_in_from_right);
            groupsUnitsViewFlipper.setOutAnimation(activity, R.anim.slide_out_to_left);
            groupsUnitsViewFlipper.showNext();
    }

    private IConversationGroup instantiateConversationGroup(String type) {
        IConversationGroup conversationGroup = null;

        try {
            Class klass = Class.forName("io.bass64.amounts.models.conversation.group." + type + "ConversationGroup");
            conversationGroup = (IConversationGroup)klass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return conversationGroup;
    }
}
