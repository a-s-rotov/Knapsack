package com.mobiquity.solver;


import com.mobiquity.exception.AlgorithmException;
import com.mobiquity.model.Item;
import com.mobiquity.model.Knapsack;
import com.mobiquity.model.Record;

import java.util.*;
import java.util.stream.Collectors;

public class BruteForceSolver implements Solver {

  //set is used to trim dead-end branches
  private final Set<String> checkSet = new HashSet<>();
  //list of all appropriate results
  private final List<Knapsack> resultList = new ArrayList<>();


  public String solve(Record record) {
    if (record.getItems() == null) {
      throw new AlgorithmException("Record does not have items");
    }
    for (Item item : record.getItems()) {
      var knapsack = new Knapsack();
      knapsack.addItem(item);
      //starting processing all roots
      //in this case root is one element from the general set
      if (checkWeight(record, knapsack)) {
        resultList.add(knapsack);
        recursiveProcess(List.of(item), record);
      }
    }
    var solution = findBestKnapsack();
    if (solution == null) {
      return "-";
    } else {
      return solution.getItems().stream()
              .map(Item::getIndex)
              .map(Objects::toString)
              .collect(Collectors.joining(","));
    }
  }

  private void recursiveProcess(List<Item> processItems, Record record) {
    var allItems = record.getItems();
    for (Item allItem : allItems) {
      if (!processItems.contains(allItem)) {
        var knapsack = new Knapsack();
        knapsack.addAllItem(processItems);
        knapsack.addItem(allItem);
        var key = knapsack.getItems().stream()
                .map(Item::getIndex)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining());
        //checking conditions: weight of knapsack < capacity and this branch is not used
        if (!checkSet.contains(key) && checkWeight(record, knapsack)) {
          checkSet.add(key);
          resultList.add(knapsack);
          recursiveProcess(knapsack.getItems(), record);
        }
      }
    }
  }

  private Knapsack findBestKnapsack() {
    var maxCost = 0d;
    List<Knapsack> knapsackCandidate = new ArrayList<>();
    //finding the best knapsack in results
    for (Knapsack knapsack : resultList) {
      if (Double.compare(maxCost, knapsack.getCost()) == 0) {
        knapsackCandidate.add(knapsack);
      } else if (Double.compare(maxCost, knapsack.getCost()) < 0) {
        // if better knapsack is found, clear the array and update max cost
        knapsackCandidate = new ArrayList<>();
        knapsackCandidate.add(knapsack);
        maxCost = knapsack.getCost();
      }
    }
    if (knapsackCandidate.size() == 0) {
      return null;
    } else {
      //finding lightweight knapsack
      return knapsackCandidate.stream()
              .reduce((knapsack, knapsack2) -> {
                if (Double.compare(knapsack.getWeight(), knapsack.getCost()) >= 0) {
                  return knapsack;
                } else {
                  return knapsack2;
                }
              })
              .orElseThrow(() -> new AlgorithmException("Calculation error, the best knapsack is not found"));
    }
  }

  private boolean checkWeight(Record record, Knapsack knapsack) {
    return Double.compare(record.getCapacity().doubleValue(), knapsack.getWeight()) >= 0;
  }
}
