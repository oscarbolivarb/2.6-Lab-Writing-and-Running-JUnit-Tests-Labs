package cs415.lab2;

public class DiscountEngine {

    public double applyDiscount(double subtotal, String code) {

        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }
        
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Discount code required");
        }

        String normalized = code.trim().toUpperCase();

        if (normalized.equals("SAVE10")) {
            
            return subtotal * 0.90;
        }

        if (normalized.equals("SAVE20")) {
            
            if (subtotal >= 100) {
                return subtotal * 0.80;
            }
            return subtotal;
        }

        
        throw new IllegalArgumentException("Unknown discount code");
    }
}

