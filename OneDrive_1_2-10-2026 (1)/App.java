package cs415.lab2;

public class App {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart(new DiscountEngine());

        cart.addItem("Book", 19.99, 2);
        cart.addItem("Mouse", 25.00, 1);

        System.out.println("Subtotal: " + cart.getSubtotal());
        cart.applyDiscount("SAVE10");
        System.out.println("Total after discount: " + cart.getTotal());
    }
}
