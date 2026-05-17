package cs415.lab2;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final DiscountEngine discountEngine;
    private final Map<String, LineItem> items = new HashMap<>();

    private String activeDiscountCode = null;

    public ShoppingCart(DiscountEngine discountEngine) {
        if (discountEngine == null) {
            throw new IllegalArgumentException("discountEngine cannot be null");
        }
        this.discountEngine = discountEngine;
    }

    public void addItem(String name, double price, int quantity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name required");
        }

        
        if (price < 0) { 
            throw new IllegalArgumentException("Price must be non-negative");
        }
        if (quantity <= 0) { 
            throw new IllegalArgumentException("Quantity must be positive");
        }

        LineItem existing = items.get(name);
        if (existing == null) {
            items.put(name, new LineItem(name, price, quantity));
        } else {
            
            if (Double.compare(existing.price, price) !=0) {
                throw new IllegalArgumentException("Existing item price cannot change");
            }
            existing.quantity = existing.quantity + quantity;
        }
    }

    public void removeItem(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Item name required");
        }

        if(!items.containsKey(name)) {
            throw new IllegalArgumentException("Item not found");
        }

        
        items.remove(name);
    }

    public double getSubtotal() {
        double sum = 0.0;
        for (LineItem li : items.values()) {
            
            sum += li.price * li.quantity;
        }
        return round2(sum);
    }

    public void applyDiscount(String code) {
        
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Discount code required");
        }
        
        
        activeDiscountCode = code;
    }

    public double getTotal() {
        double subtotal = getSubtotal();
        if (activeDiscountCode ==null) {
            return subtotal;
        }
                      
        double discounted = 
            discountEngine.applyDiscount(subtotal, activeDiscountCode);
        return round2(discounted);
    }

    private static double round2(double value) {
    
        return Math.round(value * 100.0) / 100.0;
    }

    private static class LineItem {
        private final String name;
        private final double price;
        private int quantity;

        private LineItem(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }
}
