import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Use Case 7: Sort Bogies by Capacity (Comparator)
 *
 * <p>This use case demonstrates custom sorting of bogies based on
 * seating capacity using Comparator.</p>
 *
 * @version 7.0
 */
public class UseCase7TrainConsistApp {

    // Inner Bogie class to model passenger bogies.
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

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC7 - Sort Bogies by Capacity (Comparator)");
        System.out.println("========================================\n");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("General", 90));

        System.out.println("Before Sorting:");
        printBogies(bogies);

        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        System.out.println("After Sorting by Capacity:");
        printBogies(bogies);

        System.out.println("UC7 sorting completed...");
    }

    private static void printBogies(List<Bogie> bogies) {
        for (Bogie bogie : bogies) {
            System.out.println(bogie.getName() + " -> " + bogie.getCapacity());
        }
        System.out.println();
    }
}
