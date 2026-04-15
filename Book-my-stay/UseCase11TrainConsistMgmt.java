import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Use Case 11: Validate Train ID and Cargo Code (Regex)
 *
 * <p>This use case validates input formats using regular expressions
 * with Pattern and Matcher.</p>
 *
 * @version 11.0
 */
public class UseCase11TrainConsistMgmt {

    private static final Pattern TRAIN_ID_PATTERN = Pattern.compile("^TRN-\\d{4}$");
    private static final Pattern CARGO_CODE_PATTERN = Pattern.compile("^PET-[A-Z]{2}$");

    static boolean isValidTrainId(String trainId) {
        if (trainId == null) {
            return false;
        }
        Matcher trainMatcher = TRAIN_ID_PATTERN.matcher(trainId);
        return trainMatcher.matches();
    }

    static boolean isValidCargoCode(String cargoCode) {
        if (cargoCode == null) {
            return false;
        }
        Matcher cargoMatcher = CARGO_CODE_PATTERN.matcher(cargoCode);
        return cargoMatcher.matches();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("UC11 - Validate Train ID and Cargo Code");
        System.out.println("========================================\n");

        System.out.print("Enter Train ID (Format: TRN-1234): ");
        String trainId = scanner.nextLine();

        System.out.print("Enter Cargo Code (Format: PET-AB): ");
        String cargoCode = scanner.nextLine();

        boolean isTrainIdValid = isValidTrainId(trainId);
        boolean isCargoCodeValid = isValidCargoCode(cargoCode);

        System.out.println("\nValidation Results:");
        System.out.println("Train ID Valid: " + isTrainIdValid);
        System.out.println("Cargo Code Valid: " + isCargoCodeValid + "\n");

        System.out.println("UC11 validation completed...");
        scanner.close();
    }
}
