import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class UseCase17TrainConsistMgmtTest {

    @Test
    void testSort_BasicAlphabeticalSorting() {
        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};

        UseCase17TrainConsistMgmt.sortBogieNames(bogieNames);

        assertArrayEquals(new String[]{"AC Chair", "First Class", "General", "Luxury", "Sleeper"}, bogieNames);
    }

    @Test
    void testSort_UnsortedInput() {
        String[] bogieNames = {"Luxury", "General", "Sleeper", "AC Chair"};

        UseCase17TrainConsistMgmt.sortBogieNames(bogieNames);

        assertArrayEquals(new String[]{"AC Chair", "General", "Luxury", "Sleeper"}, bogieNames);
    }

    @Test
    void testSort_AlreadySortedArray() {
        String[] bogieNames = {"AC Chair", "First Class", "General"};

        UseCase17TrainConsistMgmt.sortBogieNames(bogieNames);

        assertArrayEquals(new String[]{"AC Chair", "First Class", "General"}, bogieNames);
    }

    @Test
    void testSort_DuplicateBogieNames() {
        String[] bogieNames = {"Sleeper", "AC Chair", "Sleeper", "General"};

        UseCase17TrainConsistMgmt.sortBogieNames(bogieNames);

        assertArrayEquals(new String[]{"AC Chair", "General", "Sleeper", "Sleeper"}, bogieNames);
    }

    @Test
    void testSort_SingleElementArray() {
        String[] bogieNames = {"Sleeper"};

        UseCase17TrainConsistMgmt.sortBogieNames(bogieNames);

        assertArrayEquals(new String[]{"Sleeper"}, bogieNames);
    }
}
