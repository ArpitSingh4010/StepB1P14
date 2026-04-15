import java.util.Arrays;

/**
 * Use Case 17: Sort Bogie Names Using Arrays.sort()
 *
 * <p>This class demonstrates alphabetical sorting of bogie names
 * using Java's built-in Arrays.sort() utility method.</p>
 *
 * @version 17.0
 */
public class UseCase17TrainConsistMgmt {

    static void sortBogieNames(String[] bogieNames) {
        Arrays.sort(bogieNames);
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC17 - Sort Bogie Names Using Arrays.sort()");
        System.out.println("========================================\n");

        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};

        System.out.println("Original Bogie Names:");
        System.out.println(Arrays.toString(bogieNames));

        sortBogieNames(bogieNames);

        System.out.println("\nSorted Bogie Names (Alphabetical):");
        System.out.println(Arrays.toString(bogieNames));

        System.out.println("\nUC17 sorting completed...");
    }
}
