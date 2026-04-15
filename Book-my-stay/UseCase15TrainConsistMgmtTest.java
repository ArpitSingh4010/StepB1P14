import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase15TrainConsistMgmtTest {

    @Test
    void testCargo_SafeAssignment() {
        UseCase15TrainConsistMgmt.GoodsBogie bogie =
                new UseCase15TrainConsistMgmt.GoodsBogie("Cylindrical");

        bogie.assignCargo("Petroleum");

        assertTrue(bogie.isLastAssignmentSuccessful());
        assertEquals("Petroleum", bogie.getCargo());
    }

    @Test
    void testCargo_UnsafeAssignmentHandled() {
        UseCase15TrainConsistMgmt.GoodsBogie bogie =
                new UseCase15TrainConsistMgmt.GoodsBogie("Rectangular");

        bogie.assignCargo("Petroleum");

        assertFalse(bogie.isLastAssignmentSuccessful());
        assertEquals("Error: Unsafe cargo assignment!", bogie.getLastStatusMessage());
    }

    @Test
    void testCargo_CargoNotAssignedAfterFailure() {
        UseCase15TrainConsistMgmt.GoodsBogie bogie =
                new UseCase15TrainConsistMgmt.GoodsBogie("Rectangular");

        bogie.assignCargo("Petroleum");

        assertNull(bogie.getCargo());
    }

    @Test
    void testCargo_ProgramContinuesAfterException() {
        UseCase15TrainConsistMgmt.GoodsBogie unsafeBogie =
                new UseCase15TrainConsistMgmt.GoodsBogie("Rectangular");
        UseCase15TrainConsistMgmt.GoodsBogie safeBogie =
                new UseCase15TrainConsistMgmt.GoodsBogie("Cylindrical");

        unsafeBogie.assignCargo("Petroleum");
        safeBogie.assignCargo("Petroleum");

        assertFalse(unsafeBogie.isLastAssignmentSuccessful());
        assertTrue(safeBogie.isLastAssignmentSuccessful());
        assertEquals("Petroleum", safeBogie.getCargo());
    }

    @Test
    void testCargo_FinallyBlockExecution() {
        UseCase15TrainConsistMgmt.GoodsBogie safeBogie =
                new UseCase15TrainConsistMgmt.GoodsBogie("Cylindrical");
        UseCase15TrainConsistMgmt.GoodsBogie unsafeBogie =
                new UseCase15TrainConsistMgmt.GoodsBogie("Rectangular");

        safeBogie.assignCargo("Petroleum");
        unsafeBogie.assignCargo("Petroleum");

        assertTrue(safeBogie.isFinallyExecuted());
        assertTrue(unsafeBogie.isFinallyExecuted());
    }
}
