package com.mobiquity.loader;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.FileParseException;
import com.mobiquity.model.Item;
import com.mobiquity.model.Record;
import com.mobiquity.utils.Constants;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class DataLoader {

  private static final String REGEX_ITEMS = "(?<item>\\(.*?\\))";
  private static final String REGEX_CAPACITY = "(?<capacity>^\\d+)";
  private static final String REGEX_ITEM = "\\((?<index>\\d+),(?<weight>.+?),(?<cost>.*?)\\)";
  private static final String REGEX_CURRENCY = "(?<name>^€)(?<value>\\d*)";
  private static final Pattern itemsPattern = Pattern.compile(REGEX_ITEMS);
  private static final Pattern capacityPattern = Pattern.compile(REGEX_CAPACITY);
  private static final Pattern itemPattern = Pattern.compile(REGEX_ITEM);
  private static final Pattern currencyPattern = Pattern.compile(REGEX_CURRENCY);

  public static List<Record> parse(String filePath) throws APIException {
    var path = Paths.get(filePath);
    String data;
    try {
      data = Files.readString(path);
      data = data.trim();
    } catch (IOException | NullPointerException e) {
      throw new APIException("Wrong file", e);
    }
    String[] lines = data.split(Constants.SEPARATOR);
    List<Record> records = new ArrayList<>();
    for (String line : lines) {
      records.add(process(line));
    }
    return records;
  }

  /**
   * Method is used to parse each line
   *
   * @param string text line from the file
   * @return Record is a file storage structure
   */
  private static Record process(String string) {
    Record record = new Record();
    Matcher capacityMatcher = capacityPattern.matcher(string);
    capacityMatcher.find();
    //getting capacity
    var capacity = capacityMatcher.group("capacity");
    try {
      record.setCapacity(Integer.parseInt(capacity));
    } catch (NumberFormatException e) {
      throw new FileParseException("Cannot parse capacity", e);
    }


    Matcher itemMatcher = itemsPattern.matcher(string);
    while (itemMatcher.find()) {

      //parsing the right part of the line
      String itemString = itemMatcher.group("item");
      if (itemString != null) {
        var matcher = itemPattern.matcher(itemString);
        if (matcher.find()) {
          try {
            Matcher costMatcher = currencyPattern.matcher(matcher.group("cost"));
            String costString;
            if (costMatcher.find()) {
              costString = costMatcher.group("value");
            } else {
              throw new FileParseException("Cannont parse cost");
            }
            record.addItem(
                    Item.builder()
                            .cost(Float.parseFloat(costString))
                            .index(Integer.parseInt(matcher.group("index")))
                            .weight(Float.parseFloat(matcher.group("weight")))
                            .build());

          } catch (NumberFormatException | NullPointerException e) {
            throw new FileParseException("Cannot parse item", e);
          }

        } else {
          throw new FileParseException("Cannot parse item");
        }
      } else {
        throw new FileParseException("Cannot parse line");
      }

    }
    if (record.getItems() == null) {
      throw new FileParseException("Cannot parse file");
    }
    return record;
  }
}

