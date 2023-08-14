
package catalogue;

import middle.StockException;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;



import static org.junit.Assert.*;
import org.junit.Test;

import middle.StockException;

public class BasketTest {

    Basket basket = new Basket();
    Product p1 = new Product("0001", "40 inch tv", 200.00, 1 );

    // Testing that the basket has been created, if so it will be not null.
    @Test
    public void testBasket() {
        assertNotNull(basket);
    }

    // Checking that the setorderNumber() method works as expected.
    @Test
    public void testSetOrderNum() {
        basket.setOrderNum(4);
        assertEquals(4, basket.getOrderNum());
    }

    // Checking that GetOrderNum() method works as expected.
    @Test
    public void testGetOrderNum() {
        basket.setOrderNum(4);
        assertEquals(4, basket.getOrderNum());
    }

    // Checking that GetSavings() method works as expected.


    // Checking that GetTotalPrice() method works as expected.


    // Checking that setTotalPrice() method works as expected.


    // Checking that adding items to the basket works as expected.
    @Test
    public void testAddProduct() {
        basket.add(p1);
        if(basket.contains(p1)) {
            assert(true);
        }else {
            assert(false);
        }
    }

    // Checking that the discount button applies a discount.


    // Checking that getDetails() Method works as expected.
    @Test
    public void testGetDetails() {
        basket.getDetails();
    }

    // Checking that the removeItem() method works as expected.

}




