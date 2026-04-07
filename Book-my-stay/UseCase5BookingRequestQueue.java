import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * <p>This class demonstrates how booking requests are accepted and queued
 * in a fair and predictable order.</p>
 *
 * @version 5.0
 */
public class UseCase5BookingRequestQueue {

    /**
     * Application entry point.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Booking Request Queue");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vannathi", "Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        while (bookingQueue.hasPendingRequests()) {
            Reservation next = bookingQueue.getNextRequest();
            System.out.println(
                    "Processing booking for Guest: "
                            + next.getGuestName()
                            + ", Room Type: "
                            + next.getRoomType());
        }
    }
}

/**
 * Represents a guest booking request.
 */
class Reservation {
    private final String guestName;
    private final String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    String getGuestName() {
        return guestName;
    }

    String getRoomType() {
        return roomType;
    }
}

/**
 * Manages booking requests in FIFO order.
 */
class BookingRequestQueue {
    private final Queue<Reservation> requestQueue;

    BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    Reservation getNextRequest() {
        return requestQueue.poll();
    }

    boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}
