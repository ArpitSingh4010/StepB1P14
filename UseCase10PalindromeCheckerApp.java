public class UseCase10PalindromeCheckerApp {

    public static void main(String[] args) {
        String input = "A man a plan a canal Panama";

        String normalized = input.toLowerCase().replaceAll("[^a-z0-9]", "");

        int left = 0, right = normalized.length() - 1;
        boolean isPalindrome = true;

        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right)) {
                isPalindrome = false;
                break;
            }
            left++;
            right--;
        }

        if (isPalindrome) {
            System.out.println("\"" + input + "\" is a palindrome.");
        } else {
            System.out.println("\"" + input + "\" is not a palindrome.");
        }
    }
}
