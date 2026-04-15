import java.util.Arrays;

/**
 * Use Case 19: Binary Search for Bogie ID (Optimized Searching)
 *
 * <p>This class demonstrates efficient bogie lookup using
 * binary search on sorted string data.</p>
 *
 * @version 19.0
 */
public class UseCase19TrainConsistMgmt {

    static boolean binarySearchBogieId(String[] bogieIds, String key) {
        Arrays.sort(bogieIds);

        int low = 0;
        int high = bogieIds.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int compare = key.compareTo(bogieIds[mid]);

            if (compare == 0) {
                return true;
            }
            if (compare < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC19 - Binary Search for Bogie ID");
        System.out.println("========================================\n");

        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        String key = "BG309";

        Arrays.sort(bogieIds);

        System.out.println("Sorted Bogie IDs:");
        for (String id : bogieIds) {
            System.out.println(id);
        }

        boolean found = binarySearchBogieId(bogieIds, key);

        System.out.println();
        if (found) {
            System.out.println("Bogie " + key + " found using Binary Search.");
        } else {
            System.out.println("Bogie " + key + " not found using Binary Search.");
        }

        System.out.println("\nUC19 search completed...");
    }
}
