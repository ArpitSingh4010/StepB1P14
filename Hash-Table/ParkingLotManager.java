import java.util.*;

public class ParkingLotManager {

    enum Status { EMPTY, OCCUPIED, DELETED }

    static class Spot {
        Status status = Status.EMPTY;
        String licensePlate;
        long entryTime;
    }

    private final int capacity;
    private final Spot[] spots;
    private int occupiedCount = 0;
    private int totalProbes = 0;
    private int totalParkings = 0;
    private static final double RATE_PER_HOUR = 5.00;

    ParkingLotManager(int capacity) {
        this.capacity = capacity;
        this.spots = new Spot[capacity];
        for (int i = 0; i < capacity; i++) spots[i] = new Spot();
    }

    private int hash(String plate) {
        int h = 0;
        for (char c : plate.toCharArray()) h = h * 31 + c;
        return Math.abs(h) % capacity;
    }

    void parkVehicle(String plate) {
        int preferred = hash(plate);
        int probes = 0;
        int idx = preferred;

        while (spots[idx].status == Status.OCCUPIED) {
            probes++;
            idx = (idx + 1) % capacity;
            if (idx == preferred) {
                System.out.println("parkVehicle(\"" + plate + "\") → Parking lot is full.");
                return;
            }
        }

        spots[idx].status = Status.OCCUPIED;
        spots[idx].licensePlate = plate;
        spots[idx].entryTime = System.currentTimeMillis();
        occupiedCount++;
        totalProbes += probes;
        totalParkings++;

        StringBuilder trace = new StringBuilder("parkVehicle(\"" + plate + "\") → ");
        if (probes == 0) {
            trace.append("Assigned spot #").append(idx).append(" (0 probes)");
        } else {
            trace.append("Spot #").append(preferred);
            for (int p = 1; p < probes; p++) trace.append("... occupied");
            trace.append("... Assigned spot #").append(idx).append(" (").append(probes).append(probes == 1 ? " probe)" : " probes)");
        }
        System.out.println(trace);
    }

    void exitVehicle(String plate) {
        int preferred = hash(plate);
        int idx = preferred;

        do {
            if (spots[idx].status == Status.OCCUPIED && plate.equals(spots[idx].licensePlate)) {
                long duration = System.currentTimeMillis() - spots[idx].entryTime;
                double hours = duration / 3_600_000.0;
                double fee = Math.max(hours * RATE_PER_HOUR, RATE_PER_HOUR);
                long mins = (duration / 60000) % 60;
                long hrs = duration / 3_600_000;
                System.out.printf("exitVehicle(\"%s\") → Spot #%d freed, Duration: %dh %02dm, Fee: $%.2f%n",
                        plate, idx, hrs, mins, fee);
                spots[idx].status = Status.DELETED;
                spots[idx].licensePlate = null;
                occupiedCount--;
                return;
            }
            idx = (idx + 1) % capacity;
        } while (idx != preferred && spots[idx].status != Status.EMPTY);

        System.out.println("exitVehicle(\"" + plate + "\") → Vehicle not found.");
    }

    void getStatistics() {
        double occupancy = (occupiedCount * 100.0) / capacity;
        double avgProbes = totalParkings == 0 ? 0 : (totalProbes * 1.0 / totalParkings);
        double loadFactor = (double) occupiedCount / capacity;
        System.out.println("\n--- Parking Statistics ---");
        System.out.printf("Occupied Spots : %d / %d%n", occupiedCount, capacity);
        System.out.printf("Occupancy      : %.1f%%%n", occupancy);
        System.out.printf("Load Factor    : %.2f%n", loadFactor);
        System.out.printf("Avg Probes     : %.2f%n", avgProbes);
        System.out.printf("Total Parkings : %d%n", totalParkings);
    }

    public static void main(String[] args) throws InterruptedException {
        ParkingLotManager lot = new ParkingLotManager(500);

        lot.parkVehicle("ABC-1234");
        lot.parkVehicle("ABC-1235");
        lot.parkVehicle("XYZ-9999");
        lot.parkVehicle("MNO-4567");
        lot.parkVehicle("DLH-0001");

        Thread.sleep(500);

        lot.exitVehicle("ABC-1234");
        lot.exitVehicle("XYZ-9999");

        for (int i = 100; i < 490; i++) {
            lot.parkVehicle("VH-" + String.format("%04d", i));
        }

        lot.getStatistics();
    }
}
