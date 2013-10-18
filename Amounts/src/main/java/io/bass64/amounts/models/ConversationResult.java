package io.bass64.amounts.models;

/**
 * Created by philostler on 17/10/13.
 */
public class ConversationResult {
    public Units unit;
    public double value;

    public ConversationResult(Units unit, double value) {
        this.unit = unit;
        this.value = value;
    }

    public String toString() {
        return unit.name();
    }
}
