public class UseCase11PalindromeCheckerApp {

    static class PalindromeChecker {
        private String input;

        PalindromeChecker(String input) {
            this.input = input;
        }

        boolean checkPalindrome() {
            char[] chars = input.toCharArray();
            int left = 0, right = chars.length - 1;
            while (left < right) {
                if (chars[left] != chars[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

        String getInput() {
            return input;
        }
    }

    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker("madam");

        if (checker.checkPalindrome()) {
            System.out.println("\"" + checker.getInput() + "\" is a palindrome.");
        } else {
            System.out.println("\"" + checker.getInput() + "\" is not a palindrome.");
        }
    }
}
