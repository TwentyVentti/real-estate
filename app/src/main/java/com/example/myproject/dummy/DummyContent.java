package com.example.myproject.dummy;

import com.example.myproject.Phrase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add 3 sample items.
        for (int i = 1; i <= 3; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        Phrase phrase = new Phrase("English","French","Italian");
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position), phrase);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String phraseString;
        public final String details;
        public final Phrase phrase;

        public DummyItem(String id, String phraseString, String details, Phrase phrase) {
            this.id = id;
            this.phraseString = phraseString;
            this.details = details;
            this.phrase = phrase;
        }

        @Override
        public String toString() {
            return phraseString;
        }
    }
}