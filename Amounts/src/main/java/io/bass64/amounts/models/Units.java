package io.bass64.amounts.models;

/**
 * Created by philostler on 17/10/13.
 */
public enum Units {
    MILES_PER_HOUR("Miles Per Hour"),
    KILOMETERS_PER_HOUR("Kilometers Per Hour");

    public String title;

    Units(String title) {
        this.title = title;
    }
}
