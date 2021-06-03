package com.mobiquity.model;

import lombok.Builder;
import lombok.Data;

/**
 * {@code Item} is used to store data about an item
 */
@Data
@Builder
public class Item {
    private int index;
    private double weight;
    private double cost;
}
