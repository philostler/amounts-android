package io.bass64.amounts.models.conversation.group;

import java.util.ArrayList;

import io.bass64.amounts.models.AvailableUnits;
import io.bass64.amounts.models.ConversationResult;

/**
 * Created by philostler on 17/10/13.
 */
public interface IConversationGroup {
    public ArrayList<ConversationResult> generate(double value, AvailableUnits units);
}