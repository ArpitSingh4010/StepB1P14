import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 4: Room Search & Availability Check
 *
 * <p>This class demonstrates how guests can view available rooms without
 * modifying inventory data.</p>
 *
 * @version 4.0
 */
public class UseCase4RoomSearch {

    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        Room singleRoom = new Room("Single", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double", 2, 400, 2500.0);
        Room suiteRoom = new Room("Suite", 3, 750, 5000.0);

        inventory.setAvailability("Single", 5);
        inventory.setAvailability("Double", 3);
        inventory.setAvailability("Suite", 2);

        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);
    }
}

/**
 * Domain model that stores room details.
 */
class Room {
    private final String type;
    private final int beds;
    private final int sizeSqft;
    private final double pricePerNight;

    Room(String type, int beds, int sizeSqft, double pricePerNight) {
        this.type = type;
        this.beds = beds;
        this.sizeSqft = sizeSqft;
        this.pricePerNight = pricePerNight;
    }

    String getType() {
        return type;
    }

    int getBeds() {
        return beds;
    }

    int getSizeSqft() {
        return sizeSqft;
    }

    double getPricePerNight() {
        return pricePerNight;
    }
}

/**
 * Centralized inventory state holder for room availability.
 */
class RoomInventory {
    private final Map<String, Integer> roomAvailability = new HashMap<>();

    void setAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    Map<String, Integer> getRoomAvailability() {
        return Collections.unmodifiableMap(roomAvailability);
    }
}

/**
 * Read-only service for searching and displaying available rooms.
 */
class RoomSearchService {

    void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        printIfAvailable(singleRoom, availability);
        printIfAvailable(doubleRoom, availability);
        printIfAvailable(suiteRoom, availability);
    }

    private void printIfAvailable(Room room, Map<String, Integer> availability) {
        int count = availability.getOrDefault(room.getType(), 0);
        if (count > 0) {
            System.out.println(room.getType() + " Room:");
            System.out.println("Beds: " + room.getBeds());
            System.out.println("Size: " + room.getSizeSqft() + " sqft");
            System.out.println("Price per night: " + room.getPricePerNight());
            System.out.println("Available: " + count + "\n");
        }
    }
}
