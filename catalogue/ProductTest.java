package catalogue;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductTest {

    BetterBasket betterBasket = new BetterBasket();
    Product p1 = new Product("0004", "40 inch tv", 200.0, 1 );

    @Test
    public void testGetProductNum() {
        assertSame("0004", p1.getProductNum());

    }

    @Test
    public void testGetDescription() {
        assertSame("40 inch tv", p1.getDescription());

    }

    @Test
    public void testGetPrice() {
        assertEquals(200.0, p1.getPrice(), 0 );

    }

    @Test
    public void testGetQuantity() {
        assertEquals(1, p1.getQuantity(), 0 );

    }

    @Test
    public void testSetProductNum() {
        p1.setProductNum("23");
        assertSame("23", p1.getProductNum());


    }

    @Test
    public void testSetDescription() {
        p1.setDescription("40 inch tv");
        assertSame("40 inch tv", p1.getDescription());


    }

    @Test
    public void testSetPrice() {
        p1.setPrice(5);
        assertEquals(5, p1.getPrice(), 0 );

    }

    @Test
    public void testSetQuantity() {
        p1.setQuantity(50);
        assertEquals(50, p1.getQuantity(), 0 );

    }

}