import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase19TrainConsistMgmtTest {

    @Test
    void testBinarySearch_BogieFound() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        boolean result = UseCase19TrainConsistMgmt.binarySearchBogieId(bogieIds, "BG309");

        assertTrue(result);
    }

    @Test
    void testBinarySearch_BogieNotFound() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        boolean result = UseCase19TrainConsistMgmt.binarySearchBogieId(bogieIds, "BG999");

        assertFalse(result);
    }

    @Test
    void testBinarySearch_FirstElementMatch() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        boolean result = UseCase19TrainConsistMgmt.binarySearchBogieId(bogieIds, "BG101");

        assertTrue(result);
    }

    @Test
    void testBinarySearch_LastElementMatch() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        boolean result = UseCase19TrainConsistMgmt.binarySearchBogieId(bogieIds, "BG550");

        assertTrue(result);
    }

    @Test
    void testBinarySearch_SingleElementArray() {
        String[] bogieIds = {"BG101"};

        boolean result = UseCase19TrainConsistMgmt.binarySearchBogieId(bogieIds, "BG101");

        assertTrue(result);
    }

    @Test
    void testBinarySearch_EmptyArray() {
        String[] bogieIds = {};

        boolean result = UseCase19TrainConsistMgmt.binarySearchBogieId(bogieIds, "BG101");

        assertFalse(result);
    }

    @Test
    void testBinarySearch_UnsortedInputHandled() {
        String[] bogieIds = {"BG309", "BG101", "BG550", "BG205", "BG412"};

        boolean result = UseCase19TrainConsistMgmt.binarySearchBogieId(bogieIds, "BG205");

        assertTrue(result);
    }
}
