package io.bass64.amounts.models;

import java.util.ArrayList;

/**
 * Created by philostler on 17/10/13.
 */
public class SpeedConversation implements IConversation {
    @Override
    public ArrayList<ConversationResult> generate(double value, AvailableUnits units) {
        if(units == null) units = AvailableUnits.MILES_PER_HOUR;

        ArrayList<ConversationResult> result = null;

        switch(units) {
            case MILES_PER_HOUR:
                result = generateFromMph(value);
                break;
            default: result = generateFromMph(value); break;
        }

        return result;
    }

    private ArrayList<ConversationResult> generateFromMph(double value) {
        ArrayList<ConversationResult> result = new ArrayList<ConversationResult>();

        result.add(new ConversationResult(AvailableUnits.MILES_PER_HOUR.title, value));
        result.add(new ConversationResult(AvailableUnits.KILOMETERS_PER_HOUR.title, value * 1.60934));

        return result;
    }
}