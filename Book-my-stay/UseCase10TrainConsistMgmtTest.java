import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UseCase10TrainConsistMgmtTest {

    @Test
    void testReduce_TotalSeatCalculation() {
        List<UseCase10TrainConsistApp.Bogie> bogies = sampleBogies();
        int total = UseCase10TrainConsistApp.calculateTotalSeats(bogies);
        assertEquals(222, total);
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {
        List<UseCase10TrainConsistApp.Bogie> bogies = sampleBogies();
        int total = UseCase10TrainConsistApp.calculateTotalSeats(bogies);
        assertEquals(72 + 56 + 24 + 70, total);
    }

    @Test
    void testReduce_SingleBogieCapacity() {
        List<UseCase10TrainConsistApp.Bogie> bogies = new ArrayList<>();
        bogies.add(new UseCase10TrainConsistApp.Bogie("Single", 80));

        int total = UseCase10TrainConsistApp.calculateTotalSeats(bogies);
        assertEquals(80, total);
    }

    @Test
    void testReduce_EmptyBogieList() {
        List<UseCase10TrainConsistApp.Bogie> bogies = new ArrayList<>();

        int total = UseCase10TrainConsistApp.calculateTotalSeats(bogies);
        assertEquals(0, total);
    }

    @Test
    void testReduce_CorrectCapacityExtraction() {
        List<UseCase10TrainConsistApp.Bogie> bogies = sampleBogies();

        int total = UseCase10TrainConsistApp.calculateTotalSeats(bogies);
        assertEquals(222, total);
    }

    @Test
    void testReduce_AllBogiesIncluded() {
        List<UseCase10TrainConsistApp.Bogie> bogies = sampleBogies();

        int total = UseCase10TrainConsistApp.calculateTotalSeats(bogies);
        assertEquals(222, total);
    }

    @Test
    void testReduce_OriginalListUnchanged() {
        List<UseCase10TrainConsistApp.Bogie> bogies = sampleBogies();
        int originalSize = bogies.size();

        UseCase10TrainConsistApp.calculateTotalSeats(bogies);

        assertEquals(originalSize, bogies.size());
        assertEquals("Sleeper", bogies.get(0).getName());
        assertEquals(72, bogies.get(0).getCapacity());
        assertEquals("AC Chair", bogies.get(1).getName());
        assertEquals(56, bogies.get(1).getCapacity());
        assertEquals("First Class", bogies.get(2).getName());
        assertEquals(24, bogies.get(2).getCapacity());
        assertEquals("Sleeper", bogies.get(3).getName());
        assertEquals(70, bogies.get(3).getCapacity());
    }

    private List<UseCase10TrainConsistApp.Bogie> sampleBogies() {
        List<UseCase10TrainConsistApp.Bogie> bogies = new ArrayList<>();
        bogies.add(new UseCase10TrainConsistApp.Bogie("Sleeper", 72));
        bogies.add(new UseCase10TrainConsistApp.Bogie("AC Chair", 56));
        bogies.add(new UseCase10TrainConsistApp.Bogie("First Class", 24));
        bogies.add(new UseCase10TrainConsistApp.Bogie("Sleeper", 70));
        return bogies;
    }
}
