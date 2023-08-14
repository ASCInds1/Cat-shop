package catalogue;

import java.io.Serializable;
import java.util.Collections;

/**
 * Write a description of class BetterBasket here.
 * 
 * @author  Your Name 
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Override
  public boolean add(Product newProduct) {
    //Find the product number of the added product
    String productNumToAdd = newProduct.getProductNum();
    //Check if an existing product with the same product number is located
    boolean existingProductFound = false;

    for (Product existingProduct : this) {
      if (existingProduct.getProductNum().equals(productNumToAdd)) {
        //update the quantity of product with the new products
        existingProduct.setQuantity(existingProduct.getQuantity() + newProduct.getQuantity());
        existingProductFound = true; // if product was found it is updated
        break; //exits from loop after task
      }
    }

    if (!existingProductFound) {
      super.add(newProduct);// adds to the list if existing product is not found
    }

    return true; // once addition is completed, it returns true
  }
  // You need to add code here
}