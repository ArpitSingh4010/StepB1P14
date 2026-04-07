import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase9TrainConsistMgmtTest {

    @Test
    void testGrouping_BogiesGroupedByType() {
        Map<String, List<UseCase9TrainConsistApp.Bogie>> result =
                UseCase9TrainConsistApp.groupBogiesByType(sampleBogies());

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        Map<String, List<UseCase9TrainConsistApp.Bogie>> result =
                UseCase9TrainConsistApp.groupBogiesByType(sampleBogies());

        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_DifferentBogieTypes() {
        Map<String, List<UseCase9TrainConsistApp.Bogie>> result =
                UseCase9TrainConsistApp.groupBogiesByType(sampleBogies());

        assertEquals(3, result.keySet().size());
    }

    @Test
    void testGrouping_EmptyBogieList() {
        List<UseCase9TrainConsistApp.Bogie> bogies = new ArrayList<>();

        Map<String, List<UseCase9TrainConsistApp.Bogie>> result =
                UseCase9TrainConsistApp.groupBogiesByType(bogies);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGrouping_SingleBogieCategory() {
        List<UseCase9TrainConsistApp.Bogie> bogies = new ArrayList<>();
        bogies.add(new UseCase9TrainConsistApp.Bogie("Sleeper", 72));
        bogies.add(new UseCase9TrainConsistApp.Bogie("Sleeper", 70));

        Map<String, List<UseCase9TrainConsistApp.Bogie>> result =
                UseCase9TrainConsistApp.groupBogiesByType(bogies);

        assertEquals(1, result.keySet().size());
        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_MapContainsCorrectKeys() {
        Map<String, List<UseCase9TrainConsistApp.Bogie>> result =
                UseCase9TrainConsistApp.groupBogiesByType(sampleBogies());

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    @Test
    void testGrouping_GroupSizeValidation() {
        Map<String, List<UseCase9TrainConsistApp.Bogie>> result =
                UseCase9TrainConsistApp.groupBogiesByType(sampleBogies());

        assertEquals(2, result.get("Sleeper").size());
        assertEquals(2, result.get("AC Chair").size());
        assertEquals(1, result.get("First Class").size());
    }

    @Test
    void testGrouping_OriginalListUnchanged() {
        List<UseCase9TrainConsistApp.Bogie> bogies = sampleBogies();
        int originalSize = bogies.size();

        UseCase9TrainConsistApp.groupBogiesByType(bogies);

        assertEquals(originalSize, bogies.size());
        assertEquals("Sleeper", bogies.get(0).getName());
        assertEquals(72, bogies.get(0).getCapacity());
        assertEquals("AC Chair", bogies.get(1).getName());
        assertEquals(56, bogies.get(1).getCapacity());
        assertEquals("First Class", bogies.get(2).getName());
        assertEquals(24, bogies.get(2).getCapacity());
        assertEquals("Sleeper", bogies.get(3).getName());
        assertEquals(70, bogies.get(3).getCapacity());
        assertEquals("AC Chair", bogies.get(4).getName());
        assertEquals(60, bogies.get(4).getCapacity());
    }

    private List<UseCase9TrainConsistApp.Bogie> sampleBogies() {
        List<UseCase9TrainConsistApp.Bogie> bogies = new ArrayList<>();
        bogies.add(new UseCase9TrainConsistApp.Bogie("Sleeper", 72));
        bogies.add(new UseCase9TrainConsistApp.Bogie("AC Chair", 56));
        bogies.add(new UseCase9TrainConsistApp.Bogie("First Class", 24));
        bogies.add(new UseCase9TrainConsistApp.Bogie("Sleeper", 70));
        bogies.add(new UseCase9TrainConsistApp.Bogie("AC Chair", 60));
        return bogies;
    }
}
