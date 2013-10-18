package io.bass64.amounts.main.listeners;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.inject.Inject;

import java.util.ArrayList;

import io.bass64.amounts.ConversationAdapter;
import io.bass64.amounts.R;
import io.bass64.amounts.models.ConversationResult;
import io.bass64.amounts.models.conversation.group.ConversationGroupModel;
import io.bass64.amounts.models.conversation.group.IConversationGroup;
import roboguice.RoboGuice;

/**
 * Item click listener for Categories ListView in Main Activity
 * @author Phil Ostler - Bass64 Ltd
 */
public class CategoriesListViewOnItemClickListener implements AdapterView.OnItemClickListener {
    @Inject
    public ConversationGroupModel model;

    private Activity activity;
    private Context context;

    public CategoriesListViewOnItemClickListener(Context context) {
        this.activity = (Activity)context;
        this.context = context;

        RoboGuice.getInjector(context).injectMembersWithoutViews(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String type = ((TextView)view).getText().toString();
            model.conversationGroup = instantiateConversationGroup(type);

            TextView valueToConvert = (TextView)activity.findViewById(R.id.valueToConvert);
            ArrayList<ConversationResult> result = model.conversationGroup.generate(Double.valueOf(valueToConvert.getText().toString()), null);

            ConversationAdapter adapter = new ConversationAdapter<ConversationResult>(context,
                    R.layout.list_view_item, R.id.unitOfMeasure,
                    result);
            ListView conversions = (ListView)activity.findViewById(R.id.conversions);
            conversions.setAdapter(adapter);

            activity.getActionBar().setDisplayHomeAsUpEnabled(true);

            ViewFlipper flipper = (ViewFlipper)activity.findViewById(R.id.ccFlipper);
            flipper.setInAnimation(context, R.anim.slide_in_from_right);
            flipper.setOutAnimation(context, R.anim.slide_out_to_left);
            flipper.showNext();
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
