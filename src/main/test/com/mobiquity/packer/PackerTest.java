package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static com.mobiquity.utils.TestUtils.getDateFromFile;
import static com.mobiquity.utils.TestUtils.getPathToTestFile;
import static org.junit.jupiter.api.Assertions.*;

public class PackerTest {

    @Test
    @DisplayName("Default test")
    public void defaultTest() {
        try {
            var resultString = Packer.pack(getPathToTestFile("example_input"));
            var expectedString = getDateFromFile("example_output");
            assertEquals(resultString,expectedString);
        } catch (APIException exception) {
            exception.printStackTrace();
            fail("Method pack() throw APIException");
        }

    }

    @Test
    @DisplayName("Wrong path test")
    public void wrongPathTest() {
        assertThrows(APIException.class, () -> Packer.pack("absolutePath"));
    }

    @Test
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Load test")
    public void loadTest() {
        try {
            var resultString = Packer.pack(getPathToTestFile("big_data_input"));
            var expectedString = getDateFromFile("big_data_output");
            assertEquals(resultString, expectedString);
        } catch (APIException exception) {
            exception.printStackTrace();
            fail("Method pack throws APIException");
        }
    }

    @Test
    @DisplayName("The first wrong data test")
    public void firstWrongDataFailureTest() {
        assertThrows(APIException.class, () -> Packer.pack(getPathToTestFile("wrong_data_input")));
    }

    @Test
    @DisplayName("The second wrong data test")
    public void secondWrongDataFailureTest() {
        assertThrows(APIException.class, () -> Packer.pack(getPathToTestFile("wrong_data_input2")));
    }

}
