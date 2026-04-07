import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use Case 8: Filter Passenger Bogies Using Streams
 *
 * <p>This use case demonstrates declarative filtering of passenger bogies
 * based on seating capacity using the Stream API.</p>
 *
 * @version 8.0
 */
public class UseCase8TrainConsistApp {

    // Reusing Bogie model pattern from UC7.
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

    static List<Bogie> filterByCapacityGreaterThan(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(b -> b.getCapacity() > threshold)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("UC8 - Filter Passenger Bogies Using Streams");
        System.out.println("===============================================\n");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        System.out.println("All Bogies:");
        printBogies(bogies);

        List<Bogie> filteredBogies = filterByCapacityGreaterThan(bogies, 60);

        System.out.println("Filtered Bogies (Capacity > 60):");
        printBogies(filteredBogies);

        System.out.println("UC8 filtering completed...");
    }

    private static void printBogies(List<Bogie> bogies) {
        for (Bogie bogie : bogies) {
            System.out.println(bogie.getName() + " -> " + bogie.getCapacity());
        }
        System.out.println();
    }
}
