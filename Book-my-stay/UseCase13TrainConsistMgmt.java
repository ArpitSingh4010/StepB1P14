import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use Case 13: Performance Comparison (Loops vs Streams)
 *
 * <p>This class compares execution time of loop-based filtering
 * and stream-based filtering using System.nanoTime().</p>
 *
 * @version 13.0
 */
public class UseCase13TrainConsistMgmt {

    // Bogie model.
    static class Bogie {
        private final String type;
        private final int capacity;

        Bogie(String type, int capacity) {
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

    static List<Bogie> filterByCapacityUsingLoop(List<Bogie> bogies, int threshold) {
        List<Bogie> filtered = new ArrayList<>();
        for (Bogie bogie : bogies) {
            if (bogie.getCapacity() > threshold) {
                filtered.add(bogie);
            }
        }
        return filtered;
    }

    static List<Bogie> filterByCapacityUsingStream(List<Bogie> bogies, int threshold) {
        return bogies.stream()
                .filter(bogie -> bogie.getCapacity() > threshold)
                .collect(Collectors.toList());
    }

    static long measureLoopExecutionTime(List<Bogie> bogies, int threshold) {
        long start = System.nanoTime();
        filterByCapacityUsingLoop(bogies, threshold);
        long end = System.nanoTime();
        return end - start;
    }

    static long measureStreamExecutionTime(List<Bogie> bogies, int threshold) {
        long start = System.nanoTime();
        filterByCapacityUsingStream(bogies, threshold);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC13 - Performance Comparison (Loops vs Streams)");
        System.out.println("========================================\n");

        List<Bogie> bogies = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            int capacity = (i % 80) + 20;
            bogies.add(new Bogie("Bogie-" + i, capacity));
        }

        int threshold = 60;

        long loopExecutionTime = measureLoopExecutionTime(bogies, threshold);
        long streamExecutionTime = measureStreamExecutionTime(bogies, threshold);

        System.out.println("Loop Execution Time (ns): " + loopExecutionTime);
        System.out.println("Stream Execution Time (ns): " + streamExecutionTime + "\n");

        System.out.println("UC13 performance benchmarking completed...");
    }
}
