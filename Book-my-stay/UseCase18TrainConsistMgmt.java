/**
 * Use Case 18: Linear Search for Bogie ID (Array-Based Searching)
 *
 * <p>This class demonstrates searching for a bogie ID in an unsorted
 * array using linear search.</p>
 *
 * @version 18.0
 */
public class UseCase18TrainConsistMgmt {

    static boolean linearSearchBogieId(String[] bogieIds, String searchId) {
        for (String id : bogieIds) {
            if (id.equals(searchId)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC18 - Linear Search for Bogie ID");
        System.out.println("========================================\n");

        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        String searchId = "BG309";

        System.out.println("Available Bogie IDs:");
        for (String id : bogieIds) {
            System.out.println(id);
        }

        boolean found = linearSearchBogieId(bogieIds, searchId);

        System.out.println();
        if (found) {
            System.out.println("Bogie " + searchId + " found in train consist.");
        } else {
            System.out.println("Bogie " + searchId + " not found in train consist.");
        }

        System.out.println("\nUC18 search completed...");
    }
}
