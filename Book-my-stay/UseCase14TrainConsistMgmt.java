/**
 * Use Case 14: Handle Invalid Bogie Capacity (Custom Exception)
 *
 * <p>This class enforces capacity validation for passenger bogies
 * using a checked custom exception.</p>
 *
 * @version 14.0
 */
public class UseCase14TrainConsistMgmt {

    // Custom checked exception for invalid capacity.
    static class InvalidCapacityException extends Exception {
        InvalidCapacityException(String message) {
            super(message);
        }
    }

    // Passenger bogie model with fail-fast validation.
    static class PassengerBogie {
        private final String type;
        private final int capacity;

        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.type = type;
            this.capacity = capacity;
        }

        String getType() {
            return type;
        }

        int getCapacity() {
            return capacity;
        }
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC14 - Handle Invalid Bogie Capacity");
        System.out.println("========================================\n");

        try {
            PassengerBogie validBogie = new PassengerBogie("Sleeper", 72);
            System.out.println("Created Bogie: " + validBogie.getType() + " -> " + validBogie.getCapacity());

            // Intentional invalid creation to demonstrate exception flow.
            new PassengerBogie("AC Chair", 0);
        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nUC14 exception handling completed...");
    }
}
