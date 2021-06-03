package com.mobiquity.packer;

import com.mobiquity.solver.BruteForceSolver;
import com.mobiquity.solver.Solver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mobiquity.utils.DataUtils.getDefaultRecord;
import static com.mobiquity.utils.DataUtils.getEqualCostRecord;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BruteForceSolverTest {

    @Test
    @DisplayName("Default test")
    public void defaultTest() {
        Solver solver = new BruteForceSolver();
        try {
            String solve = solver.solve(getDefaultRecord());
            assertEquals("1,2", solve);
        } catch (Exception exception) {
            exception.printStackTrace();
            fail("Method pack() throw APIException");
        }
    }

    @Test
    @DisplayName("Equal cost test")
    public void equalCostTest() {
        Solver solver = new BruteForceSolver();
        try {
            String solve = solver.solve(getEqualCostRecord());
            assertEquals("1", solve);
        } catch (Exception exception) {
            exception.printStackTrace();
            fail("Method pack() throw APIException");
        }
    }

}
