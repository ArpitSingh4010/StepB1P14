import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase12TrainConsistMgmtTest {

    @Test
    void testSafety_AllBogiesValid() {
        List<UseCase12TrainConsistMgmt.GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new UseCase12TrainConsistMgmt.GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new UseCase12TrainConsistMgmt.GoodsBogie("Open", "Coal"));
        bogies.add(new UseCase12TrainConsistMgmt.GoodsBogie("Box", "Grain"));

        boolean result = UseCase12TrainConsistMgmt.isTrainSafetyCompliant(bogies);
        assertTrue(result);
    }

    @Test
    void testSafety_CylindricalWithInvalidCargo() {
        List<UseCase12TrainConsistMgmt.GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new UseCase12TrainConsistMgmt.GoodsBogie("Cylindrical", "Coal"));

        boolean result = UseCase12TrainConsistMgmt.isTrainSafetyCompliant(bogies);
        assertFalse(result);
    }

    @Test
    void testSafety_NonCylindricalBogiesAllowed() {
        List<UseCase12TrainConsistMgmt.GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new UseCase12TrainConsistMgmt.GoodsBogie("Open", "Coal"));
        bogies.add(new UseCase12TrainConsistMgmt.GoodsBogie("Box", "Grain"));

        boolean result = UseCase12TrainConsistMgmt.isTrainSafetyCompliant(bogies);
        assertTrue(result);
    }

    @Test
    void testSafety_MixedBogiesWithViolation() {
        List<UseCase12TrainConsistMgmt.GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new UseCase12TrainConsistMgmt.GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new UseCase12TrainConsistMgmt.GoodsBogie("Open", "Coal"));
        bogies.add(new UseCase12TrainConsistMgmt.GoodsBogie("Cylindrical", "Coal"));

        boolean result = UseCase12TrainConsistMgmt.isTrainSafetyCompliant(bogies);
        assertFalse(result);
    }

    @Test
    void testSafety_EmptyBogieList() {
        List<UseCase12TrainConsistMgmt.GoodsBogie> bogies = new ArrayList<>();

        boolean result = UseCase12TrainConsistMgmt.isTrainSafetyCompliant(bogies);
        assertTrue(result);
    }
}
