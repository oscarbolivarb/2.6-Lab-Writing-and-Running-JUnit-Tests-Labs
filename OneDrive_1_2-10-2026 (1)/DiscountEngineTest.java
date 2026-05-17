package cs415.lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountEngineTest {
    
    private DiscountEngine  discountEngine;

    @BeforeEach
    public void setup() {
        discountEngine = new DiscountEngine();
    }

    @Test
    public void shouldApplyTenPercentDiscountWhenCodeIsSave10() {

        double result =
            discountEngine.applyDiscount(100.00, "SAVE10");
        
        assertEquals(90.00, result, 0.001);
    }

    @Test
    public void shouldThrowExceptionWhenDiscountCodeIsUnknown() {

        assertThrows(
            IllegalArgumentException.class,
            () -> discountEngine.applyDiscount(50.00, "BADCODE")
        );
    }
}