package io.bass64.amounts.models.conversation.group;

import java.util.ArrayList;

import io.bass64.amounts.models.Units;
import io.bass64.amounts.models.ConversationResult;

/**
 * Created by philostler on 17/10/13.
 */
public class SpeedConversationGroup implements IConversationGroup {
    @Override
    public ArrayList<ConversationResult> generate(double value, Units units) {
        if(units == null) units = Units.MILES_PER_HOUR;

        ArrayList<ConversationResult> result;

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

        result.add(new ConversationResult(Units.MILES_PER_HOUR, value));
        result.add(new ConversationResult(Units.KILOMETERS_PER_HOUR, value * 1.60934));

        return result;
    }
}