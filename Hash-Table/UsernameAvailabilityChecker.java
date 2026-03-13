import java.util.*;

public class UsernameAvailabilityChecker {

    private Map<String, Integer> usernameToUserId = new HashMap<>();
    private Map<String, Integer> attemptFrequency = new HashMap<>();

    UsernameAvailabilityChecker() {
        usernameToUserId.put("admin", 1);
        usernameToUserId.put("john_doe", 2);
        usernameToUserId.put("jane_smith", 3);
        usernameToUserId.put("superuser", 4);
    }

    boolean checkAvailability(String username) {
        attemptFrequency.merge(username, 1, Integer::sum);
        return !usernameToUserId.containsKey(username);
    }

    void register(String username, int userId) {
        usernameToUserId.put(username, userId);
    }

    List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String numbered = username + i;
            if (!usernameToUserId.containsKey(numbered)) {
                suggestions.add(numbered);
            }
        }
        String dotVariant = username.replace("_", ".");
        if (!usernameToUserId.containsKey(dotVariant) && !dotVariant.equals(username)) {
            suggestions.add(dotVariant);
        }
        String underscoreVariant = username.replace(".", "_");
        if (!usernameToUserId.containsKey(underscoreVariant) && !underscoreVariant.equals(username)) {
            suggestions.add(underscoreVariant);
        }
        return suggestions;
    }

    String getMostAttempted() {
        return attemptFrequency.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey() + " (" + e.getValue() + " attempts)")
                .orElse("No attempts yet");
    }

    public static void main(String[] args) {
        UsernameAvailabilityChecker checker = new UsernameAvailabilityChecker();

        System.out.println("checkAvailability(\"john_doe\")  → " + checker.checkAvailability("john_doe"));
        System.out.println("checkAvailability(\"jane_smith\") → " + checker.checkAvailability("jane_smith"));
        System.out.println("checkAvailability(\"admin\")      → " + checker.checkAvailability("admin"));
        System.out.println("checkAvailability(\"admin\")      → " + checker.checkAvailability("admin"));
        System.out.println("checkAvailability(\"admin\")      → " + checker.checkAvailability("admin"));

        System.out.println("\nsuggestAlternatives(\"john_doe\") → " + checker.suggestAlternatives("john_doe"));

        System.out.println("\ngetMostAttempted() → " + checker.getMostAttempted());
    }
}
