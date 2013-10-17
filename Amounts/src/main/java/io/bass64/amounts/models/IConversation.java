package io.bass64.amounts.models;

import java.util.ArrayList;

/**
 * Created by philostler on 17/10/13.
 */
public interface IConversation {
    public ArrayList<ConversationResult> generate(double value, AvailableUnits units);
}