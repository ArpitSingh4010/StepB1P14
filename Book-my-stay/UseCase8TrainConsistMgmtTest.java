import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase8TrainConsistMgmtTest {

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        List<UseCase8TrainConsistApp.Bogie> bogies = sampleBogies();

        List<UseCase8TrainConsistApp.Bogie> result =
                UseCase8TrainConsistApp.filterByCapacityGreaterThan(bogies, 70);

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(b -> b.getName().equals("Sleeper")));
        assertTrue(result.stream().anyMatch(b -> b.getName().equals("General")));
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        List<UseCase8TrainConsistApp.Bogie> bogies = sampleBogies();

        List<UseCase8TrainConsistApp.Bogie> result =
                UseCase8TrainConsistApp.filterByCapacityGreaterThan(bogies, 72);

        assertTrue(result.stream().noneMatch(b -> b.getName().equals("Sleeper")));
    }

    @Test
    void testFilter_CapacityLessThanThreshold() {
        List<UseCase8TrainConsistApp.Bogie> bogies = sampleBogies();

        List<UseCase8TrainConsistApp.Bogie> result =
                UseCase8TrainConsistApp.filterByCapacityGreaterThan(bogies, 70);

        assertTrue(result.stream().noneMatch(b -> b.getName().equals("AC Chair")));
        assertTrue(result.stream().noneMatch(b -> b.getName().equals("First Class")));
    }

    @Test
    void testFilter_MultipleBogiesMatching() {
        List<UseCase8TrainConsistApp.Bogie> bogies = sampleBogies();

        List<UseCase8TrainConsistApp.Bogie> result =
                UseCase8TrainConsistApp.filterByCapacityGreaterThan(bogies, 50);

        assertEquals(3, result.size());
    }

    @Test
    void testFilter_NoBogiesMatching() {
        List<UseCase8TrainConsistApp.Bogie> bogies = sampleBogies();

        List<UseCase8TrainConsistApp.Bogie> result =
                UseCase8TrainConsistApp.filterByCapacityGreaterThan(bogies, 100);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        List<UseCase8TrainConsistApp.Bogie> bogies = sampleBogies();

        List<UseCase8TrainConsistApp.Bogie> result =
                UseCase8TrainConsistApp.filterByCapacityGreaterThan(bogies, 10);

        assertEquals(bogies.size(), result.size());
    }

    @Test
    void testFilter_EmptyBogieList() {
        List<UseCase8TrainConsistApp.Bogie> bogies = new ArrayList<>();

        List<UseCase8TrainConsistApp.Bogie> result =
                UseCase8TrainConsistApp.filterByCapacityGreaterThan(bogies, 60);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        List<UseCase8TrainConsistApp.Bogie> bogies = sampleBogies();
        int originalSize = bogies.size();

        UseCase8TrainConsistApp.filterByCapacityGreaterThan(bogies, 60);

        assertEquals(originalSize, bogies.size());
        assertEquals("Sleeper", bogies.get(0).getName());
        assertEquals("AC Chair", bogies.get(1).getName());
        assertEquals("First Class", bogies.get(2).getName());
        assertEquals("General", bogies.get(3).getName());
    }

    private List<UseCase8TrainConsistApp.Bogie> sampleBogies() {
        List<UseCase8TrainConsistApp.Bogie> bogies = new ArrayList<>();
        bogies.add(new UseCase8TrainConsistApp.Bogie("Sleeper", 72));
        bogies.add(new UseCase8TrainConsistApp.Bogie("AC Chair", 56));
        bogies.add(new UseCase8TrainConsistApp.Bogie("First Class", 24));
        bogies.add(new UseCase8TrainConsistApp.Bogie("General", 90));
        return bogies;
    }
}
