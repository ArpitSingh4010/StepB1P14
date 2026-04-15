/**
 * Use Case 15: Safe Cargo Assignment Using try-catch-finally
 *
 * <p>This class demonstrates runtime safety handling for cargo assignment
 * using a custom runtime exception and structured exception blocks.</p>
 *
 * @version 15.0
 */
public class UseCase15TrainConsistMgmt {

    // Custom runtime exception for cargo safety violations.
    static class CargoSafetyException extends RuntimeException {
        CargoSafetyException(String message) {
            super(message);
        }
    }

    // Goods bogie model.
    static class GoodsBogie {
        private final String shape;
        private String cargo;
        private boolean lastAssignmentSuccessful;
        private String lastStatusMessage;
        private boolean finallyExecuted;

        GoodsBogie(String shape) {
            this.shape = shape;
        }

        void assignCargo(String cargo) {
            finallyExecuted = false;
            try {
                // Safety rule: Rectangular bogie cannot carry petroleum.
                if ("Rectangular".equalsIgnoreCase(shape)
                        && "Petroleum".equalsIgnoreCase(cargo)) {
                    throw new CargoSafetyException("Unsafe cargo assignment!");
                }

                this.cargo = cargo;
                lastAssignmentSuccessful = true;
                lastStatusMessage = "Cargo assigned successfully -> " + cargo;
                System.out.println(lastStatusMessage);
            } catch (CargoSafetyException e) {
                lastAssignmentSuccessful = false;
                lastStatusMessage = "Error: " + e.getMessage();
                System.out.println(lastStatusMessage);
            } finally {
                finallyExecuted = true;
                System.out.println("Cargo validation completed for " + shape + " bogie");
            }
        }

        String getShape() {
            return shape;
        }

        String getCargo() {
            return cargo;
        }

        boolean isLastAssignmentSuccessful() {
            return lastAssignmentSuccessful;
        }

        String getLastStatusMessage() {
            return lastStatusMessage;
        }

        boolean isFinallyExecuted() {
            return finallyExecuted;
        }
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC15 - Safe Cargo Assignment");
        System.out.println("========================================\n");

        GoodsBogie cylindricalBogie = new GoodsBogie("Cylindrical");
        cylindricalBogie.assignCargo("Petroleum");

        System.out.println();

        GoodsBogie rectangularBogie = new GoodsBogie("Rectangular");
        rectangularBogie.assignCargo("Petroleum");

        System.out.println("\nUC15 runtime handling completed...");
    }
}
