import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class UseCase12PalindromeCheckerApp {

    interface PalindromeStrategy {
        boolean check(String s);
    }

    static class StackStrategy implements PalindromeStrategy {
        public boolean check(String s) {
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                stack.push(c);
            }
            for (char c : s.toCharArray()) {
                if (c != stack.pop()) {
                    return false;
                }
            }
            return true;
        }
    }

    static class DequeStrategy implements PalindromeStrategy {
        public boolean check(String s) {
            Deque<Character> deque = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                deque.add(c);
            }
            while (deque.size() > 1) {
                if (deque.removeFirst() != deque.removeLast()) {
                    return false;
                }
            }
            return true;
        }
    }

    static class PalindromeChecker {
        private PalindromeStrategy strategy;

        PalindromeChecker(PalindromeStrategy strategy) {
            this.strategy = strategy;
        }

        boolean check(String s) {
            return strategy.check(s);
        }
    }

    public static void main(String[] args) {
        String input = "level";

        PalindromeChecker stackChecker = new PalindromeChecker(new StackStrategy());
        PalindromeChecker dequeChecker = new PalindromeChecker(new DequeStrategy());

        System.out.println("Stack Strategy:  \"" + input + "\" is " + (stackChecker.check(input) ? "" : "not ") + "a palindrome.");
        System.out.println("Deque Strategy:  \"" + input + "\" is " + (dequeChecker.check(input) ? "" : "not ") + "a palindrome.");
    }
}
