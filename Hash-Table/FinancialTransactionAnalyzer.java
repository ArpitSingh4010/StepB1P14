import java.util.*;

public class FinancialTransactionAnalyzer {

    static class Transaction {
        int id;
        double amount;
        String merchant;
        String account;
        long timestamp;

        Transaction(int id, double amount, String merchant, String account, long timestamp) {
            this.id = id;
            this.amount = amount;
            this.merchant = merchant;
            this.account = account;
            this.timestamp = timestamp;
        }

        public String toString() {
            return "(id:" + id + ", $" + amount + ", " + merchant + ")";
        }
    }

    private List<Transaction> transactions = new ArrayList<>();

    void addTransaction(Transaction t) {
        transactions.add(t);
    }

    List<int[]> findTwoSum(double target) {
        Map<Double, Transaction> seen = new HashMap<>();
        List<int[]> results = new ArrayList<>();
        for (Transaction t : transactions) {
            double complement = target - t.amount;
            if (seen.containsKey(complement)) {
                results.add(new int[]{seen.get(complement).id, t.id});
            }
            seen.put(t.amount, t);
        }
        System.out.print("findTwoSum(target=" + target + ") → ");
        if (results.isEmpty()) System.out.println("No pairs found.");
        else results.forEach(p -> System.out.println("[id:" + p[0] + ", id:" + p[1] + "]"));
        return results;
    }

    List<int[]> findTwoSumWithinWindow(double target, long windowMillis) {
        List<int[]> results = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            Map<Double, Integer> seen = new HashMap<>();
            for (int j = i; j < transactions.size(); j++) {
                Transaction t = transactions.get(j);
                if (t.timestamp - transactions.get(i).timestamp > windowMillis) break;
                double complement = target - t.amount;
                if (seen.containsKey(complement)) {
                    results.add(new int[]{seen.get(complement), t.id});
                }
                seen.put(t.amount, t.id);
            }
        }
        System.out.print("findTwoSumWithinWindow(target=" + target + ", window=" + (windowMillis / 60000) + "min) → ");
        if (results.isEmpty()) System.out.println("No pairs found.");
        else results.forEach(p -> System.out.println("[id:" + p[0] + ", id:" + p[1] + "]"));
        return results;
    }

    void detectDuplicates() {
        Map<String, List<Transaction>> index = new HashMap<>();
        for (Transaction t : transactions) {
            String key = t.amount + "|" + t.merchant;
            index.computeIfAbsent(key, k -> new ArrayList<>()).add(t);
        }
        System.out.println("detectDuplicates() →");
        boolean found = false;
        for (Map.Entry<String, List<Transaction>> e : index.entrySet()) {
            List<Transaction> group = e.getValue();
            Set<String> accounts = new HashSet<>();
            for (Transaction t : group) accounts.add(t.account);
            if (group.size() > 1 && accounts.size() > 1) {
                String[] parts = e.getKey().split("\\|");
                System.out.println("  amount=$" + parts[0] + ", merchant=" + parts[1] + ", accounts=" + accounts);
                found = true;
            }
        }
        if (!found) System.out.println("  No duplicates detected.");
    }

    void findKSum(int k, double target, int start, List<Transaction> current, List<List<Integer>> results) {
        if (k == 0 && Math.abs(target) < 0.001) {
            List<Integer> ids = new ArrayList<>();
            for (Transaction t : current) ids.add(t.id);
            results.add(ids);
            return;
        }
        if (k == 0 || start >= transactions.size()) return;
        for (int i = start; i < transactions.size(); i++) {
            current.add(transactions.get(i));
            findKSum(k - 1, target - transactions.get(i).amount, i + 1, current, results);
            current.remove(current.size() - 1);
        }
    }

    void findKSumWrapper(int k, double target) {
        List<List<Integer>> results = new ArrayList<>();
        findKSum(k, target, 0, new ArrayList<>(), results);
        System.out.print("findKSum(k=" + k + ", target=" + target + ") → ");
        if (results.isEmpty()) System.out.println("No combinations found.");
        else results.forEach(r -> System.out.println(r));
    }

    public static void main(String[] args) {
        FinancialTransactionAnalyzer analyzer = new FinancialTransactionAnalyzer();

        long now = System.currentTimeMillis();
        analyzer.addTransaction(new Transaction(1, 500, "Store A", "acc1", now));
        analyzer.addTransaction(new Transaction(2, 300, "Store B", "acc2", now + 900_000));
        analyzer.addTransaction(new Transaction(3, 200, "Store C", "acc3", now + 1_800_000));
        analyzer.addTransaction(new Transaction(4, 500, "Store A", "acc4", now + 2_000_000));
        analyzer.addTransaction(new Transaction(5, 150, "Store D", "acc5", now + 2_200_000));

        analyzer.findTwoSum(500);
        analyzer.findTwoSumWithinWindow(500, 3_600_000);
        analyzer.detectDuplicates();
        analyzer.findKSumWrapper(3, 1000);
    }
}
