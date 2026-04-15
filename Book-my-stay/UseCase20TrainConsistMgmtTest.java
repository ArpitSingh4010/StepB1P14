import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase20TrainConsistMgmtTest {

    @Test
    void testSearch_ThrowsExceptionWhenEmpty() {
        String[] bogieIds = {};

        assertThrows(IllegalStateException.class,
                () -> UseCase20TrainConsistMgmt.searchBogieId(bogieIds, "BG101"));
    }

    @Test
    void testSearch_AllowsSearchWhenDataExists() {
        String[] bogieIds = {"BG101", "BG205"};

        assertDoesNotThrow(() -> UseCase20TrainConsistMgmt.searchBogieId(bogieIds, "BG101"));
    }

    @Test
    void testSearch_BogieFoundAfterValidation() {
        String[] bogieIds = {"BG101", "BG205", "BG309"};

        boolean result = UseCase20TrainConsistMgmt.searchBogieId(bogieIds, "BG205");

        assertTrue(result);
    }

    @Test
    void testSearch_BogieNotFoundAfterValidation() {
        String[] bogieIds = {"BG101", "BG205", "BG309"};

        boolean result = UseCase20TrainConsistMgmt.searchBogieId(bogieIds, "BG999");

        assertFalse(result);
    }

    @Test
    void testSearch_SingleElementValidCase() {
        String[] bogieIds = {"BG101"};

        boolean result = UseCase20TrainConsistMgmt.searchBogieId(bogieIds, "BG101");

        assertTrue(result);
    }
}
