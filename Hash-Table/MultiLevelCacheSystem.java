import java.util.*;

public class MultiLevelCacheSystem {

    static class VideoData {
        String videoId;
        String content;
        int accessCount;

        VideoData(String videoId, String content) {
            this.videoId = videoId;
            this.content = content;
            this.accessCount = 0;
        }
    }

    private final int l1Capacity;
    private final int l2Capacity;
    private final int promoteThreshold;

    private final LinkedHashMap<String, VideoData> l1Cache;
    private final LinkedHashMap<String, VideoData> l2Cache;
    private final Map<String, VideoData> l3Database = new HashMap<>();

    private int l1Hits, l1Misses, l2Hits, l2Misses, l3Hits, l3Misses;
    private long l1TotalTime, l2TotalTime, l3TotalTime;
    private int totalRequests;

    MultiLevelCacheSystem(int l1Capacity, int l2Capacity, int promoteThreshold) {
        this.l1Capacity = l1Capacity;
        this.l2Capacity = l2Capacity;
        this.promoteThreshold = promoteThreshold;

        this.l1Cache = new LinkedHashMap<>(16, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> eldest) {
                if (size() > l1Capacity) {
                    l2Cache.put(eldest.getKey(), eldest.getValue());
                    return true;
                }
                return false;
            }
        };

        this.l2Cache = new LinkedHashMap<>(16, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, VideoData> eldest) {
                return size() > l2Capacity;
            }
        };
    }

    void seedDatabase(String videoId, String content) {
        l3Database.put(videoId, new VideoData(videoId, content));
    }

    void invalidate(String videoId) {
        l1Cache.remove(videoId);
        l2Cache.remove(videoId);
        System.out.println("invalidate(\"" + videoId + "\") → Evicted from L1 and L2.");
    }

    String getVideo(String videoId) {
        totalRequests++;
        long start;
        System.out.println("\ngetVideo(\"" + videoId + "\")");

        start = System.nanoTime();
        if (l1Cache.containsKey(videoId)) {
            l1Hits++;
            long t = System.nanoTime() - start;
            l1TotalTime += t;
            l1Cache.get(videoId).accessCount++;
            System.out.printf("  → L1 Cache HIT  (%.3f ms)%n", t / 1_000_000.0);
            return l1Cache.get(videoId).content;
        }
        l1Misses++;
        l1TotalTime += System.nanoTime() - start;
        System.out.printf("  → L1 Cache MISS%n");

        start = System.nanoTime();
        if (l2Cache.containsKey(videoId)) {
            l2Hits++;
            VideoData data = l2Cache.get(videoId);
            data.accessCount++;
            long t = System.nanoTime() - start;
            l2TotalTime += t;
            System.out.printf("  → L2 Cache HIT  (%.3f ms)%n", t / 1_000_000.0);
            if (data.accessCount >= promoteThreshold) {
                l1Cache.put(videoId, data);
                l2Cache.remove(videoId);
                System.out.println("  → Promoted to L1 (accessCount=" + data.accessCount + ")");
            }
            return data.content;
        }
        l2Misses++;
        l2TotalTime += System.nanoTime() - start;
        System.out.printf("  → L2 Cache MISS%n");

        start = System.nanoTime();
        if (l3Database.containsKey(videoId)) {
            l3Hits++;
            VideoData data = l3Database.get(videoId);
            data.accessCount++;
            long t = System.nanoTime() - start;
            l3TotalTime += t;
            System.out.printf("  → L3 Database HIT (%.3f ms)%n", t / 1_000_000.0);
            l2Cache.put(videoId, data);
            System.out.println("  → Added to L2 (accessCount=" + data.accessCount + ")");
            return data.content;
        }
        l3Misses++;
        System.out.println("  → L3 Database MISS — video not found.");
        return null;
    }

    void getStatistics() {
        System.out.println("\n=== Cache Statistics ===");
        printLevelStats("L1", l1Hits, l1Misses, l1TotalTime, l1Hits + l1Misses);
        printLevelStats("L2", l2Hits, l2Misses, l2TotalTime, l2Hits + l2Misses);
        printLevelStats("L3", l3Hits, l3Misses, l3TotalTime, l3Hits + l3Misses);
        int totalHits = l1Hits + l2Hits + l3Hits;
        double overallHitRate = totalRequests == 0 ? 0 : (totalHits * 100.0 / totalRequests);
        long totalTime = l1TotalTime + l2TotalTime + l3TotalTime;
        double avgTime = totalRequests == 0 ? 0 : (totalTime / 1_000_000.0 / totalRequests);
        System.out.printf("Overall  | Hit Rate: %5.1f%% | Avg Time: %.3f ms | Total Requests: %d%n",
                overallHitRate, avgTime, totalRequests);
    }

    private void printLevelStats(String level, int hits, int misses, long totalNs, int reqs) {
        double hitRate = reqs == 0 ? 0 : (hits * 100.0 / reqs);
        double avgTime = reqs == 0 ? 0 : (totalNs / 1_000_000.0 / reqs);
        System.out.printf("%-8s | Hit Rate: %5.1f%% | Avg Time: %.3f ms | Hits: %d | Misses: %d%n",
                level, hitRate, avgTime, hits, misses);
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem(3, 10, 3);

        for (int i = 1; i <= 20; i++) {
            cache.seedDatabase("video_" + i, "Content of video " + i);
        }

        cache.getVideo("video_1");
        cache.getVideo("video_2");
        cache.getVideo("video_999");
        cache.getVideo("video_1");
        cache.getVideo("video_3");
        cache.getVideo("video_2");
        cache.getVideo("video_2");
        cache.getVideo("video_2");
        cache.getVideo("video_1");
        cache.getVideo("video_5");

        cache.invalidate("video_1");
        cache.getVideo("video_1");

        cache.getStatistics();
    }
}
