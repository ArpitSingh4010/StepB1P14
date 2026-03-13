import java.util.*;
import java.util.concurrent.*;

public class FlashSaleInventoryManager {

    private Map<String, Integer> inventory = new ConcurrentHashMap<>();
    private Map<String, Queue<Integer>> waitingList = new LinkedHashMap<>();

    FlashSaleInventoryManager() {
        inventory.put("IPHONE15_256GB", 100);
        inventory.put("PS5_CONSOLE", 50);
        inventory.put("MACBOOK_PRO_M3", 30);
        waitingList.put("IPHONE15_256GB", new LinkedList<>());
        waitingList.put("PS5_CONSOLE", new LinkedList<>());
        waitingList.put("MACBOOK_PRO_M3", new LinkedList<>());
    }

    int checkStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }

    String purchaseItem(String productId, int userId) {
        if (!inventory.containsKey(productId)) {
            return "Product not found.";
        }
        synchronized (this) {
            int stock = inventory.get(productId);
            if (stock > 0) {
                inventory.put(productId, stock - 1);
                return "Success for user " + userId + ", " + (stock - 1) + " units remaining.";
            } else {
                Queue<Integer> queue = waitingList.get(productId);
                queue.add(userId);
                return "Out of stock. User " + userId + " added to waiting list, position #" + queue.size();
            }
        }
    }

    void displayWaitingList(String productId) {
        Queue<Integer> queue = waitingList.getOrDefault(productId, new LinkedList<>());
        System.out.println("Waiting list for " + productId + ": " + queue);
    }

    public static void main(String[] args) {
        FlashSaleInventoryManager manager = new FlashSaleInventoryManager();

        System.out.println("checkStock(\"IPHONE15_256GB\") → " + manager.checkStock("IPHONE15_256GB") + " units available");
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 12345));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 67890));

        System.out.println("\nSimulating 98 more purchases to exhaust stock...");
        for (int i = 1; i <= 98; i++) {
            manager.purchaseItem("IPHONE15_256GB", 10000 + i);
        }

        System.out.println("\nStock after 100 purchases: " + manager.checkStock("IPHONE15_256GB") + " units");
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 99999));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 88888));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 77777));

        System.out.println();
        manager.displayWaitingList("IPHONE15_256GB");
    }
}
