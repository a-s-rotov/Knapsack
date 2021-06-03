package com.mobiquity.utils;

import com.mobiquity.model.Item;
import com.mobiquity.model.Record;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataUtils {

  public static Record getDefaultRecord() {
    var items = new ArrayList<Item>();
    items.add(Item.builder()
            .cost(1)
            .index(1)
            .weight(1)
            .build());

    items.add(Item.builder()
            .cost(1)
            .index(2)
            .weight(1)
            .build());

    items.add(Item.builder()
            .cost(1)
            .index(3)
            .weight(2)
            .build());
    var record = new Record();
    record.setCapacity(3);
    record.setItems(items);

    return record;
  }

  public static Record getEqualCostRecord() {
    var items = new ArrayList<Item>();
    items.add(Item.builder()
            .cost(1)
            .index(1)
            .weight(1)
            .build());

    items.add(Item.builder()
            .cost(1)
            .index(2)
            .weight(2)
            .build());

    items.add(Item.builder()
            .cost(1)
            .index(3)
            .weight(2)
            .build());
    var record = new Record();
    record.setCapacity(2);
    record.setItems(items);
    return record;
  }
}
