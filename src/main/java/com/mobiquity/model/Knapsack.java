package com.mobiquity.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code Knapsack} is used to store data about an appropriate solution
 */
@Data
public class Knapsack {

  private List<Item> items;
  private double weight = 0;
  private double cost = 0;

  /**
   * Adds a new item to the storage
   *
   * @param item new item
   * @return Returns total weight
   */
  public double addItem(Item item) {
    if (items == null) {
      items = new ArrayList<>();
    }
    items.add(item);
    weight = items.stream().map(Item::getWeight).mapToDouble(Double::doubleValue).sum();
    cost = items.stream().map(Item::getCost).mapToDouble(Double::doubleValue).sum();
    return weight;
  }

  /**
   * Adds an array to the storage
   *
   * @param items array
   * @return Returns total weight
   */
  public double addAllItem(List<Item> items) {
    for (Item item : items) {
      weight = addItem(item);
    }
    return weight;
  }
}
