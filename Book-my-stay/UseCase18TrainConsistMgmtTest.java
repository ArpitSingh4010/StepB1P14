import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase18TrainConsistMgmtTest {

    @Test
    void testSearch_BogieFound() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        boolean result = UseCase18TrainConsistMgmt.linearSearchBogieId(bogieIds, "BG309");

        assertTrue(result);
    }

    @Test
    void testSearch_BogieNotFound() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        boolean result = UseCase18TrainConsistMgmt.linearSearchBogieId(bogieIds, "BG999");

        assertFalse(result);
    }

    @Test
    void testSearch_FirstElementMatch() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        boolean result = UseCase18TrainConsistMgmt.linearSearchBogieId(bogieIds, "BG101");

        assertTrue(result);
    }

    @Test
    void testSearch_LastElementMatch() {
        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        boolean result = UseCase18TrainConsistMgmt.linearSearchBogieId(bogieIds, "BG550");

        assertTrue(result);
    }

    @Test
    void testSearch_SingleElementArray() {
        String[] bogieIds = {"BG101"};

        boolean result = UseCase18TrainConsistMgmt.linearSearchBogieId(bogieIds, "BG101");

        assertTrue(result);
    }
}
