package cs415.lab2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    public void setup() {
        cart = new ShoppingCart(new DiscountEngine());
    } 
    
    @AfterEach
    public void tearDown() {
        cart = null;
    } 
    
    @Test
    public void shouldCalculateSubtotalForOneItem() {
        cart.addItem("Book", 19.99, 2);

        assertEquals(39.98, cart.getSubtotal(), 0.001);
    }

    @Test
    public void shouldCalculateSubtotalForMultipleItems() {
        cart.addItem("Book", 19.99, 2);
        cart.addItem("Mouse", 25.00, 1);

        assertEquals(64.98, cart.getSubtotal(), 0.001);
    }

    @Test
    public void shouldThrowExceptionWhenQuantityIsZero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> cart.addItem("Book", 10.00, 0)
        );
    }

    @Test
    public void shouldApplySave10DiscountToTotal() {
        cart.addItem("Book", 100.00, 1);
        cart.applyDiscount("SAVE10");

        assertEquals(90.00, cart.getTotal(), 0.001);
    }
}