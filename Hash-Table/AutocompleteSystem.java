import java.util.*;

public class AutocompleteSystem {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd = false;
    }

    private TrieNode root = new TrieNode();
    private Map<String, Integer> frequency = new HashMap<>();
    private Map<String, List<String>> prefixCache = new HashMap<>();

    void addQuery(String query, int freq) {
        frequency.put(query, freq);
        prefixCache.clear();
        TrieNode node = root;
        for (char c : query.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    void updateFrequency(String query) {
        if (!frequency.containsKey(query)) {
            addQuery(query, 1);
        } else {
            frequency.merge(query, 1, Integer::sum);
            prefixCache.clear();
        }
        System.out.println("updateFrequency(\"" + query + "\") → Frequency: " + frequency.get(query));
    }

    private void collectQueries(TrieNode node, String current, List<String> results) {
        if (node.isEnd) results.add(current);
        for (Map.Entry<Character, TrieNode> e : node.children.entrySet()) {
            collectQueries(e.getValue(), current + e.getKey(), results);
        }
    }

    List<String> search(String prefix) {
        long start = System.nanoTime();

        if (prefixCache.containsKey(prefix)) {
            long elapsed = System.nanoTime() - start;
            List<String> cached = prefixCache.get(prefix);
            printResults(prefix, cached, elapsed, true);
            return cached;
        }

        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                System.out.println("search(\"" + prefix + "\") → No suggestions found.");
                return Collections.emptyList();
            }
            node = node.children.get(c);
        }

        List<String> matches = new ArrayList<>();
        collectQueries(node, prefix, matches);

        PriorityQueue<String> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(q -> frequency.getOrDefault(q, 0))
        );
        for (String q : matches) {
            minHeap.offer(q);
            if (minHeap.size() > 10) minHeap.poll();
        }

        List<String> topK = new ArrayList<>(minHeap);
        topK.sort((a, b) -> frequency.getOrDefault(b, 0) - frequency.getOrDefault(a, 0));
        prefixCache.put(prefix, topK);

        long elapsed = System.nanoTime() - start;
        printResults(prefix, topK, elapsed, false);
        return topK;
    }

    private void printResults(String prefix, List<String> results, long elapsedNs, boolean cached) {
        System.out.printf("%nsearch(\"%s\") → %s [%.2f ms]%n", prefix, cached ? "(cached)" : "", elapsedNs / 1_000_000.0);
        for (int i = 0; i < results.size(); i++) {
            String q = results.get(i);
            System.out.printf("  %2d. \"%s\" (%,d searches)%n", i + 1, q, frequency.getOrDefault(q, 0));
        }
    }

    public static void main(String[] args) {
        AutocompleteSystem ac = new AutocompleteSystem();

        ac.addQuery("java tutorial", 1_234_567);
        ac.addQuery("javascript", 987_654);
        ac.addQuery("java download", 456_789);
        ac.addQuery("java 21 features", 345_000);
        ac.addQuery("java vs python", 298_000);
        ac.addQuery("java spring boot", 230_000);
        ac.addQuery("java interview questions", 198_000);
        ac.addQuery("java arraylist", 150_000);
        ac.addQuery("java hashmap", 140_000);
        ac.addQuery("java stream api", 120_000);
        ac.addQuery("javascript tutorial", 890_000);
        ac.addQuery("javascript vs python", 310_000);

        ac.search("jav");
        ac.search("java");
        ac.search("jav");

        System.out.println();
        ac.updateFrequency("java 21 features");
        ac.updateFrequency("java 21 features");
        ac.updateFrequency("java 21 features");
    }
}
