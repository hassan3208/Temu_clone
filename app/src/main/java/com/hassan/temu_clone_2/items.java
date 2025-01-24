package com.hassan.temu_clone_2;

import java.util.ArrayList;
import java.util.List;

public class items {

    private static List<items> itemList = new ArrayList<>(); // Static list to store items

    private int imageResId; // Drawable resource ID
    private String name;    // Item name
    private String price;   // Item price

    // Constructor
    public items(int imageResId, String name, String price) {
        this.imageResId = imageResId;
        this.name = name;
        this.price = price;

        // Add the created item to the static list
        itemList.add(this);
    }

    // Getter for the static list
    public static List<items> getItemList() {
        return itemList;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
