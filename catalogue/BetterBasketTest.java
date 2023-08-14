package catalogue;



import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BetterBasketTest {
    private BetterBasket betterBasket;

    @Before
    public void setUp() {
        betterBasket = new BetterBasket();
    }

    @Test
    public void testAddNewProduct() {
        Product newProduct = new Product("P123", "Sample Product", 10.0, 2);
        assertTrue(betterBasket.add(newProduct));
        assertEquals(1, betterBasket.size());
    }

    @Test
    public void testAddExistingProduct() {
        Product existingProduct = new Product("P123", "Existing Product", 15.0, 3);
        betterBasket.add(existingProduct);

        Product newProduct = new Product("P123", "Sample Product", 10.0, 2);
        assertTrue(betterBasket.add(newProduct));

        assertEquals(1, betterBasket.size());
        assertEquals(5, existingProduct.getQuantity()); // Quantity should be updated
    }

    @Test
    public void testAddMultipleProducts() {
        Product product1 = new Product("0004", "Product 1", 10.0, 2);
        Product product2 = new Product("0003", "Product 2", 20.0, 3);
        Product product3 = new Product("0002", "Product 3", 5.0, 1);

        assertTrue(betterBasket.add(product1));
        assertTrue(betterBasket.add(product2));
        assertTrue(betterBasket.add(product3));

        assertEquals(3, betterBasket.size());
    }
}

