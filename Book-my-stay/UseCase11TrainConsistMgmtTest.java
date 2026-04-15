import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCase11TrainConsistMgmtTest {

    @Test
    void testRegex_ValidTrainID() {
        assertTrue(UseCase11TrainConsistMgmt.isValidTrainId("TRN-1234"));
    }

    @Test
    void testRegex_InvalidTrainIDFormat() {
        assertFalse(UseCase11TrainConsistMgmt.isValidTrainId("TRAIN12"));
        assertFalse(UseCase11TrainConsistMgmt.isValidTrainId("TRN12A"));
        assertFalse(UseCase11TrainConsistMgmt.isValidTrainId("1234-TRN"));
    }

    @Test
    void testRegex_ValidCargoCode() {
        assertTrue(UseCase11TrainConsistMgmt.isValidCargoCode("PET-AB"));
    }

    @Test
    void testRegex_InvalidCargoCodeFormat() {
        assertFalse(UseCase11TrainConsistMgmt.isValidCargoCode("PET-ab"));
        assertFalse(UseCase11TrainConsistMgmt.isValidCargoCode("PET123"));
        assertFalse(UseCase11TrainConsistMgmt.isValidCargoCode("AB-PET"));
    }

    @Test
    void testRegex_TrainIDDigitLengthValidation() {
        assertFalse(UseCase11TrainConsistMgmt.isValidTrainId("TRN-123"));
        assertFalse(UseCase11TrainConsistMgmt.isValidTrainId("TRN-12345"));
    }

    @Test
    void testRegex_CargoCodeUppercaseValidation() {
        assertFalse(UseCase11TrainConsistMgmt.isValidCargoCode("PET-aB"));
        assertFalse(UseCase11TrainConsistMgmt.isValidCargoCode("PET-Ab"));
    }

    @Test
    void testRegex_EmptyInputHandling() {
        assertFalse(UseCase11TrainConsistMgmt.isValidTrainId(""));
        assertFalse(UseCase11TrainConsistMgmt.isValidCargoCode(""));
    }

    @Test
    void testRegex_ExactPatternMatch() {
        assertFalse(UseCase11TrainConsistMgmt.isValidTrainId("XXTRN-1234"));
        assertFalse(UseCase11TrainConsistMgmt.isValidTrainId("TRN-1234YY"));
        assertFalse(UseCase11TrainConsistMgmt.isValidCargoCode("XXPET-AB"));
        assertFalse(UseCase11TrainConsistMgmt.isValidCargoCode("PET-ABYY"));
    }

    @Test
    void testRegex_OriginalInputIntegrity() {
        String trainId = "TRN-6524";
        String cargoCode = "PET-FH";

        UseCase11TrainConsistMgmt.isValidTrainId(trainId);
        UseCase11TrainConsistMgmt.isValidCargoCode(cargoCode);

        assertEquals("TRN-6524", trainId);
        assertEquals("PET-FH", cargoCode);
    }
}
