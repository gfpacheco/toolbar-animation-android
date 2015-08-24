package com.gfpacheco.toolbaranimation.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("01"));
        addItem(new DummyItem("02"));
        addItem(new DummyItem("03"));
        addItem(new DummyItem("04"));
        addItem(new DummyItem("05"));
        addItem(new DummyItem("06"));
        addItem(new DummyItem("07"));
        addItem(new DummyItem("08"));
        addItem(new DummyItem("09"));
        addItem(new DummyItem("10"));
        addItem(new DummyItem("11"));
        addItem(new DummyItem("12"));
        addItem(new DummyItem("13"));
        addItem(new DummyItem("14"));
        addItem(new DummyItem("15"));
        addItem(new DummyItem("16"));
        addItem(new DummyItem("17"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;
        public String picture;

        public DummyItem(String id) {
            this.id = id;
            this.content = "Item " + id;
            this.picture = id + ".jpg";
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
