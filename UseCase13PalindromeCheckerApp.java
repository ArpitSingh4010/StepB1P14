import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class UseCase13PalindromeCheckerApp {

    static boolean twoPointer(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    static boolean stackBased(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) stack.push(c);
        for (char c : s.toCharArray()) {
            if (c != stack.pop()) return false;
        }
        return true;
    }

    static boolean dequeBased(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) deque.add(c);
        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) return false;
        }
        return true;
    }

    static boolean stringBuilderBased(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    static boolean recursiveBased(String s, int start, int end) {
        if (start >= end) return true;
        if (s.charAt(start) != s.charAt(end)) return false;
        return recursiveBased(s, start + 1, end - 1);
    }

    static long measure(Runnable r) {
        long start = System.nanoTime();
        r.run();
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {
        String input = "amanaplanacanalpanama";

        long t1 = measure(() -> twoPointer(input));
        long t2 = measure(() -> stackBased(input));
        long t3 = measure(() -> dequeBased(input));
        long t4 = measure(() -> stringBuilderBased(input));
        long t5 = measure(() -> recursiveBased(input, 0, input.length() - 1));

        System.out.println("Input: \"" + input + "\"");
        System.out.println("-------------------------------------------");
        System.out.printf("%-25s %d ns%n", "Two Pointer:",      t1);
        System.out.printf("%-25s %d ns%n", "Stack:",            t2);
        System.out.printf("%-25s %d ns%n", "Deque:",            t3);
        System.out.printf("%-25s %d ns%n", "StringBuilder:",    t4);
        System.out.printf("%-25s %d ns%n", "Recursive:",        t5);
        System.out.println("-------------------------------------------");

        long fastest = Math.min(t1, Math.min(t2, Math.min(t3, Math.min(t4, t5))));
        String winner = fastest == t1 ? "Two Pointer"
                      : fastest == t2 ? "Stack"
                      : fastest == t3 ? "Deque"
                      : fastest == t4 ? "StringBuilder"
                      : "Recursive";
        System.out.println("Fastest: " + winner + " (" + fastest + " ns)");
    }
}
