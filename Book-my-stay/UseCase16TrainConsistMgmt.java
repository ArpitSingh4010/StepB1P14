/**
 * Use Case 16: Sort Passenger Bogies by Capacity (Bubble Sort)
 *
 * <p>This class demonstrates manual sorting using Bubble Sort
 * without relying on library sort methods.</p>
 *
 * @version 16.0
 */
public class UseCase16TrainConsistMgmt {

    static void bubbleSort(int[] capacities) {
        for (int i = 0; i < capacities.length - 1; i++) {
            for (int j = 0; j < capacities.length - 1 - i; j++) {
                if (capacities[j] > capacities[j + 1]) {
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC16 - Manual Sorting using Bubble Sort");
        System.out.println("========================================\n");

        int[] capacities = {72, 56, 24, 70, 60};

        System.out.println("Original Capacities:");
        for (int capacity : capacities) {
            System.out.print(capacity + " ");
        }

        bubbleSort(capacities);

        System.out.println("\n\nSorted Capacities (Ascending):");
        for (int capacity : capacities) {
            System.out.print(capacity + " ");
        }

        System.out.println("\n\nUC16 sorting completed...");
    }
}
