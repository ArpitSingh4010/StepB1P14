import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase13TrainConsistMgmtTest {

    @Test
    void testLoopFilteringLogic() {
        List<UseCase13TrainConsistMgmt.Bogie> bogies = sampleBogies();

        List<UseCase13TrainConsistMgmt.Bogie> result =
                UseCase13TrainConsistMgmt.filterByCapacityUsingLoop(bogies, 60);

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(b -> b.getCapacity() > 60));
    }

    @Test
    void testStreamFilteringLogic() {
        List<UseCase13TrainConsistMgmt.Bogie> bogies = sampleBogies();

        List<UseCase13TrainConsistMgmt.Bogie> result =
                UseCase13TrainConsistMgmt.filterByCapacityUsingStream(bogies, 60);

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(b -> b.getCapacity() > 60));
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<UseCase13TrainConsistMgmt.Bogie> bogies = sampleBogies();

        List<UseCase13TrainConsistMgmt.Bogie> loopResult =
                UseCase13TrainConsistMgmt.filterByCapacityUsingLoop(bogies, 60);
        List<UseCase13TrainConsistMgmt.Bogie> streamResult =
                UseCase13TrainConsistMgmt.filterByCapacityUsingStream(bogies, 60);

        assertEquals(loopResult.size(), streamResult.size());
    }

    @Test
    void testExecutionTimeMeasurement() {
        List<UseCase13TrainConsistMgmt.Bogie> bogies = sampleBogies();

        long loopTime = UseCase13TrainConsistMgmt.measureLoopExecutionTime(bogies, 60);
        long streamTime = UseCase13TrainConsistMgmt.measureStreamExecutionTime(bogies, 60);

        assertTrue(loopTime > 0);
        assertTrue(streamTime > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<UseCase13TrainConsistMgmt.Bogie> largeBogies = new ArrayList<>();
        for (int i = 1; i <= 50000; i++) {
            int capacity = (i % 100) + 1;
            largeBogies.add(new UseCase13TrainConsistMgmt.Bogie("Bogie-" + i, capacity));
        }

        List<UseCase13TrainConsistMgmt.Bogie> loopResult =
                UseCase13TrainConsistMgmt.filterByCapacityUsingLoop(largeBogies, 60);
        List<UseCase13TrainConsistMgmt.Bogie> streamResult =
                UseCase13TrainConsistMgmt.filterByCapacityUsingStream(largeBogies, 60);

        long expectedCount = largeBogies.stream()
                .filter(b -> b.getCapacity() > 60)
                .count();

        assertEquals(expectedCount, loopResult.size());
        assertEquals(expectedCount, streamResult.size());
    }

    private List<UseCase13TrainConsistMgmt.Bogie> sampleBogies() {
        List<UseCase13TrainConsistMgmt.Bogie> bogies = new ArrayList<>();
        bogies.add(new UseCase13TrainConsistMgmt.Bogie("Sleeper", 72));
        bogies.add(new UseCase13TrainConsistMgmt.Bogie("AC Chair", 56));
        bogies.add(new UseCase13TrainConsistMgmt.Bogie("First Class", 24));
        bogies.add(new UseCase13TrainConsistMgmt.Bogie("Sleeper", 70));
        bogies.add(new UseCase13TrainConsistMgmt.Bogie("AC Chair", 61));
        return bogies;
    }
}
