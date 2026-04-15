import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UseCase14TrainConsistMgmtTest {

    @Test
    void testException_ValidCapacityCreation() throws UseCase14TrainConsistMgmt.InvalidCapacityException {
        UseCase14TrainConsistMgmt.PassengerBogie bogie =
                new UseCase14TrainConsistMgmt.PassengerBogie("Sleeper", 72);

        assertEquals("Sleeper", bogie.getType());
        assertEquals(72, bogie.getCapacity());
    }

    @Test
    void testException_NegativeCapacityThrowsException() {
        assertThrows(UseCase14TrainConsistMgmt.InvalidCapacityException.class,
                () -> new UseCase14TrainConsistMgmt.PassengerBogie("Sleeper", -10));
    }

    @Test
    void testException_ZeroCapacityThrowsException() {
        assertThrows(UseCase14TrainConsistMgmt.InvalidCapacityException.class,
                () -> new UseCase14TrainConsistMgmt.PassengerBogie("AC Chair", 0));
    }

    @Test
    void testException_ExceptionMessageValidation() {
        UseCase14TrainConsistMgmt.InvalidCapacityException exception = assertThrows(
                UseCase14TrainConsistMgmt.InvalidCapacityException.class,
                () -> new UseCase14TrainConsistMgmt.PassengerBogie("First Class", 0)
        );

        assertEquals("Capacity must be greater than zero", exception.getMessage());
    }

    @Test
    void testException_ObjectIntegrityAfterCreation() throws UseCase14TrainConsistMgmt.InvalidCapacityException {
        UseCase14TrainConsistMgmt.PassengerBogie bogie =
                new UseCase14TrainConsistMgmt.PassengerBogie("First Class", 24);

        assertEquals("First Class", bogie.getType());
        assertEquals(24, bogie.getCapacity());
    }

    @Test
    void testException_MultipleValidBogiesCreation() throws UseCase14TrainConsistMgmt.InvalidCapacityException {
        UseCase14TrainConsistMgmt.PassengerBogie bogie1 =
                new UseCase14TrainConsistMgmt.PassengerBogie("Sleeper", 72);
        UseCase14TrainConsistMgmt.PassengerBogie bogie2 =
                new UseCase14TrainConsistMgmt.PassengerBogie("AC Chair", 56);
        UseCase14TrainConsistMgmt.PassengerBogie bogie3 =
                new UseCase14TrainConsistMgmt.PassengerBogie("First Class", 24);

        assertEquals("Sleeper", bogie1.getType());
        assertEquals(72, bogie1.getCapacity());
        assertEquals("AC Chair", bogie2.getType());
        assertEquals(56, bogie2.getCapacity());
        assertEquals("First Class", bogie3.getType());
        assertEquals(24, bogie3.getCapacity());
    }
}
