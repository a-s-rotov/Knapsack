package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.AlgorithmException;
import com.mobiquity.exception.FileParseException;
import com.mobiquity.model.Record;
import com.mobiquity.solver.BruteForceSolver;
import com.mobiquity.solver.Solver;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.StringJoiner;

import static com.mobiquity.loader.DataLoader.parse;
import static com.mobiquity.utils.Constants.SEPARATOR;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Packer {


  public static String pack(String filePath) throws APIException {
    List<Record> records;
    //load data
    try {
      records = parse(filePath);
    } catch (FileParseException e) {
      throw new APIException("File parsing error", e);
    }
    var result = new StringJoiner(SEPARATOR);

    //data processing line by line
    for (Record record : records) {
      Solver solver = new BruteForceSolver();
      try {
        result.add(solver.solve(record));
      } catch (AlgorithmException e) {
        throw new APIException("Solution could not be found", e);
      }

    }
    return result.toString();
  }
}