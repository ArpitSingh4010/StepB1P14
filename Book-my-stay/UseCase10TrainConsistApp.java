import java.util.ArrayList;
import java.util.List;

/**
 * Use Case 10: Count Total Seats in Train (reduce)
 *
 * <p>This use case aggregates bogie seating capacities into a single
 * total value using Stream reduction.</p>
 *
 * @version 10.0
 */
public class UseCase10TrainConsistApp {

    // Reusing Bogie model.
    static class Bogie {
        private final String name;
        private final int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        String getName() {
            return name;
        }

        int getCapacity() {
            return capacity;
        }
    }

    static int calculateTotalSeats(List<Bogie> bogies) {
        return bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC10 - Count Total Seats in Train");
        System.out.println("========================================\n");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));

        System.out.println("Bogies in Train:");
        for (Bogie b : bogies) {
            System.out.println(b.getName() + " -> " + b.getCapacity());
        }

        int totalSeats = calculateTotalSeats(bogies);
        System.out.println("\nTotal Seating Capacity of Train: " + totalSeats + "\n");

        System.out.println("UC10 aggregation completed...");
    }
}
