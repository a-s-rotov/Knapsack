package com.mobiquity.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

    public static String getPathToTestFile(String name){
        return Paths.get("src", "main", "test", "resources", name)
                .toAbsolutePath()
                .toString();
    }

    public static String getDateFromFile(String name){
        var path = Paths.get("src", "main", "test", "resources", name)
                .toAbsolutePath();
        try {
            return Files.readString(path).trim();
        } catch (IOException e) {
            //We throw a RuntimeException because if the file is wrong than the test just fails.
            //This is acceptable behavior.
            throw new UncheckedIOException(e);
        }
    }
}
