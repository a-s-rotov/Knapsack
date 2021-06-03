package com.mobiquity.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code Record} is used to store a line from the file
 */
@Data
public class Record {
    private List<Item> items;
    private Integer capacity;

    public void addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }
}
