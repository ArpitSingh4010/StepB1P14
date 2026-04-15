/**
 * Use Case 20: Exception Handling During Search Operations
 *
 * <p>This class applies fail-fast validation before search operations
 * by throwing an IllegalStateException when no bogies exist.</p>
 *
 * @version 20.0
 */
public class UseCase20TrainConsistMgmt {

    static boolean searchBogieId(String[] bogieIds, String searchId) {
        if (bogieIds.length == 0) {
            throw new IllegalStateException("No bogies available in train. Cannot perform search.");
        }

        for (String id : bogieIds) {
            if (id.equals(searchId)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC20 - Exception Handling During Search");
        System.out.println("========================================\n");

        // Empty train scenario to demonstrate fail-fast behavior.
        String[] bogieIds = {};
        String searchId = "BG101";

        boolean found = searchBogieId(bogieIds, searchId);

        if (found) {
            System.out.println("Bogie " + searchId + " found in train consist.");
        } else {
            System.out.println("Bogie " + searchId + " not found in train consist.");
        }

        System.out.println("\nUC20 execution completed...");
    }
}
