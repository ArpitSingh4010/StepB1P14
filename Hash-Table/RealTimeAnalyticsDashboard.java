import java.util.*;
import java.util.stream.*;

public class RealTimeAnalyticsDashboard {

    private Map<String, Integer> pageViews = new HashMap<>();
    private Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    private Map<String, Integer> trafficSources = new HashMap<>();
    private int totalEvents = 0;

    void processEvent(String url, String userId, String source) {
        pageViews.merge(url, 1, Integer::sum);
        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);
        trafficSources.merge(source, 1, Integer::sum);
        totalEvents++;
    }

    void getDashboard() {
        System.out.println("=== Real-Time Analytics Dashboard ===");
        System.out.println("Total Events Processed: " + totalEvents);

        System.out.println("\nTop Pages:");
        pageViews.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .forEach(e -> {
                    int unique = uniqueVisitors.getOrDefault(e.getKey(), Collections.emptySet()).size();
                    System.out.printf("  %-40s %,6d views  (%,d unique)%n",
                            e.getKey(), e.getValue(), unique);
                });

        System.out.println("\nTraffic Sources:");
        trafficSources.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> {
                    double pct = totalEvents == 0 ? 0 : (e.getValue() * 100.0 / totalEvents);
                    System.out.printf("  %-15s %,6d visits  (%.1f%%)%n", e.getKey(), e.getValue(), pct);
                });
    }

    public static void main(String[] args) {
        RealTimeAnalyticsDashboard dashboard = new RealTimeAnalyticsDashboard();

        String[] pages = {
            "/article/breaking-news", "/sports/championship",
            "/tech/ai-update", "/politics/election", "/entertainment/oscars"
        };
        String[] sources = {"google", "facebook", "direct", "twitter", "other"};
        Random rand = new Random(42);

        int[] weights = {40, 25, 15, 12, 8};

        for (int i = 0; i < 500; i++) {
            String page = pages[rand.nextInt(pages.length)];
            String user = "user_" + rand.nextInt(200);
            int pick = rand.nextInt(100);
            int cumulative = 0;
            String source = sources[sources.length - 1];
            for (int s = 0; s < weights.length; s++) {
                cumulative += weights[s];
                if (pick < cumulative) { source = sources[s]; break; }
            }
            dashboard.processEvent(page, user, source);
        }

        dashboard.getDashboard();
    }
}
