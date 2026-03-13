import java.util.*;

public class PlagiarismDetector {

    private final int n;
    private Map<String, Set<String>> ngramIndex = new HashMap<>();

    PlagiarismDetector(int n) {
        this.n = n;
    }

    private List<String> extractNgrams(String text) {
        String[] words = text.toLowerCase().replaceAll("[^a-z0-9 ]", "").split("\\s+");
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < i + n; j++) {
                if (j > i) sb.append(" ");
                sb.append(words[j]);
            }
            ngrams.add(sb.toString());
        }
        return ngrams;
    }

    void indexDocument(String docId, String content) {
        List<String> ngrams = extractNgrams(content);
        for (String ngram : ngrams) {
            ngramIndex.computeIfAbsent(ngram, k -> new HashSet<>()).add(docId);
        }
        System.out.println("Indexed \"" + docId + "\" → " + ngrams.size() + " " + n + "-grams extracted.");
    }

    void analyzeDocument(String docId, String content) {
        List<String> ngrams = extractNgrams(content);
        System.out.println("\nanalyzeDocument(\"" + docId + "\")");
        System.out.println("→ Extracted " + ngrams.size() + " " + n + "-grams");

        Map<String, Integer> matchCounts = new HashMap<>();
        for (String ngram : ngrams) {
            Set<String> docs = ngramIndex.getOrDefault(ngram, Collections.emptySet());
            for (String other : docs) {
                if (!other.equals(docId)) {
                    matchCounts.merge(other, 1, Integer::sum);
                }
            }
        }

        if (matchCounts.isEmpty()) {
            System.out.println("→ No matching documents found.");
            return;
        }

        matchCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> {
                    double similarity = (e.getValue() * 100.0) / ngrams.size();
                    String verdict = similarity >= 50 ? "PLAGIARISM DETECTED"
                                   : similarity >= 15 ? "suspicious"
                                   : "acceptable";
                    System.out.printf("→ Found %d matching n-grams with \"%s\" → Similarity: %.1f%% (%s)%n",
                            e.getValue(), e.getKey(), similarity, verdict);
                });
    }

    public static void main(String[] args) {
        PlagiarismDetector detector = new PlagiarismDetector(5);

        String essay089 = "The impact of climate change on global ecosystems is a well-documented phenomenon that affects biodiversity and human livelihoods across every continent on earth.";
        String essay092 = "The quick brown fox jumps over the lazy dog and the impact of climate change on global ecosystems is well documented and affects biodiversity across every continent.";
        String essay123 = "The impact of climate change on global ecosystems is a well-documented phenomenon that affects biodiversity and human livelihoods across every continent on earth demonstrating the urgency.";

        detector.indexDocument("essay_089.txt", essay089);
        detector.indexDocument("essay_092.txt", essay092);

        detector.analyzeDocument("essay_123.txt", essay123);
    }
}
