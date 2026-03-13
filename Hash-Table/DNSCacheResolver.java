import java.util.*;

public class DNSCacheResolver {

    static class DNSEntry {
        String domain;
        String ipAddress;
        long expiryTime;

        DNSEntry(String domain, String ipAddress, int ttlSeconds) {
            this.domain = domain;
            this.ipAddress = ipAddress;
            this.expiryTime = System.currentTimeMillis() + (ttlSeconds * 1000L);
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    private final int maxCapacity;
    private final LinkedHashMap<String, DNSEntry> cache;
    private int hits = 0;
    private int misses = 0;
    private long totalLookupTime = 0;
    private int totalLookups = 0;

    private final Map<String, String> upstreamDNS = new HashMap<>();

    DNSCacheResolver(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.cache = new LinkedHashMap<>(16, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, DNSEntry> eldest) {
                return size() > maxCapacity;
            }
        };
        upstreamDNS.put("google.com",    "172.217.14.206");
        upstreamDNS.put("github.com",    "140.82.114.4");
        upstreamDNS.put("amazon.com",    "176.32.98.166");
        upstreamDNS.put("openai.com",    "104.18.32.47");
        upstreamDNS.put("stackoverflow.com", "151.101.1.69");
    }

    String resolve(String domain) {
        long start = System.nanoTime();
        totalLookups++;
        String result;

        DNSEntry entry = cache.get(domain);

        if (entry != null && !entry.isExpired()) {
            hits++;
            result = "Cache HIT  → " + entry.ipAddress;
        } else {
            misses++;
            String ip = upstreamDNS.getOrDefault(domain, "0.0.0.0");
            int ttl = 300;
            DNSEntry newEntry = new DNSEntry(domain, ip, ttl);
            cache.put(domain, newEntry);
            result = (entry != null ? "Cache EXPIRED" : "Cache MISS ") + " → Query upstream → " + ip + " (TTL: " + ttl + "s)";
        }

        long elapsed = System.nanoTime() - start;
        totalLookupTime += elapsed;
        System.out.printf("resolve(\"%-20s) → %s  [%.3f ms]%n", domain + "\"", result, elapsed / 1_000_000.0);
        return result;
    }

    void evictExpired() {
        cache.entrySet().removeIf(e -> e.getValue().isExpired());
    }

    void getCacheStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);
        double avgLookup = totalLookups == 0 ? 0 : (totalLookupTime / 1_000_000.0 / totalLookups);
        System.out.println("\n--- Cache Stats ---");
        System.out.printf("Hits: %d | Misses: %d | Hit Rate: %.1f%%%n", hits, misses, hitRate);
        System.out.printf("Avg Lookup Time: %.3f ms | Cached Entries: %d/%d%n", avgLookup, cache.size(), maxCapacity);
    }

    public static void main(String[] args) throws InterruptedException {
        DNSCacheResolver dns = new DNSCacheResolver(5);

        dns.resolve("google.com");
        dns.resolve("google.com");
        dns.resolve("github.com");
        dns.resolve("amazon.com");
        dns.resolve("openai.com");
        dns.resolve("stackoverflow.com");
        dns.resolve("google.com");
        dns.resolve("github.com");

        dns.getCacheStats();
    }
}
