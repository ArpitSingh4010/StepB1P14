public class UseCase8PalindromeCheckerApp {
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }
    static Node buildLinkedList(String s) {
        Node head = null, tail = null;
        for (char c : s.toCharArray()) {
            Node newNode = new Node(c);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        return head;
    }
    static Node findMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static Node reverse(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    static boolean isPalindrome(String s) {
        Node head = buildLinkedList(s);
        Node mid = findMiddle(head);
        Node secondHalf = reverse(mid);
        Node first = head, second = secondHalf;
        boolean result = true;
        while (second != null) {
            if (first.data != second.data) {
                result = false;
                break;
            }
            first = first.next;
            second = second.next;
        }

        return result;
    }

    public static void main(String[] args) {
        String input = "racecar";

        if (isPalindrome(input)) {
            System.out.println("\"" + input + "\" is a palindrome.");
        } else {
            System.out.println("\"" + input + "\" is not a palindrome.");
        }
    }
}
