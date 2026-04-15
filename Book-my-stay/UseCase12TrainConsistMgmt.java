import java.util.ArrayList;
import java.util.List;

/**
 * Use Case 12: Safety Compliance Check for Goods Bogies
 *
 * <p>This class enforces domain safety rules on goods bogies.
 * It validates cylindrical bogies so they carry only petroleum.</p>
 *
 * @version 12.0
 */
public class UseCase12TrainConsistMgmt {

    // Goods bogie model.
    static class GoodsBogie {
        private final String type;
        private final String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        String getType() {
            return type;
        }

        String getCargo() {
            return cargo;
        }
    }

    @FunctionalInterface
    interface GoodsBogieSafetyRule {
        boolean isSafe(GoodsBogie bogie);
    }

    static boolean isTrainSafetyCompliant(List<GoodsBogie> goodsBogies) {
        GoodsBogieSafetyRule safetyRule = bogie ->
                !"Cylindrical".equalsIgnoreCase(bogie.getType())
                        || "Petroleum".equalsIgnoreCase(bogie.getCargo());

        return goodsBogies.stream().allMatch(safetyRule::isSafe);
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("UC12 - Safety Compliance Check for Goods Bogies");
        System.out.println("========================================\n");

        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal"));

        System.out.println("Goods Bogies in Train:");
        for (GoodsBogie bogie : goodsBogies) {
            System.out.println(bogie.getType() + " -> " + bogie.getCargo());
        }

        boolean isSafe = isTrainSafetyCompliant(goodsBogies);
        System.out.println("\nSafety Compliance Status: " + isSafe);
        System.out.println(isSafe
                ? "Train formation is SAFE."
                : "Train formation is NOT SAFE.");

        System.out.println("\nUC12 safety validation completed...");
    }
}
