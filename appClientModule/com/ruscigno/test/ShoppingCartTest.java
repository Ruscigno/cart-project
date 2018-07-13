/**
 * 
 */
package com.ruscigno.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import com.ruscigno.cart.ShoppingCart;
import com.ruscigno.cart.Product;

/**
 * @author Sander
 *
 */
public class ShoppingCartTest {

	private static final BigDecimal PRICE1 = new BigDecimal(591.97);
	private static final BigDecimal PRICE2 = new BigDecimal(394.77);
	private static final BigDecimal PRICE3 = new BigDecimal(144.07);
	private static final BigDecimal PRICE4 = new BigDecimal(27.9);
	
	private Product prod1;
	private Product prod2;
	private Product prod3;
	private Product prod4;
	
	private ShoppingCart shoppingCart;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		shoppingCart = new ShoppingCart();
		
		prod1 = new Product(1L, "Java Senior Course");
		prod2 = new Product(2L, "Java Pleno Course");
		prod3 = new Product(3L, "Java Junior Course");
		prod4 = new Product(4L, "Java Basic Couse");
	}

	@Test
	public void addItemTest() {
		shoppingCart.addItem(prod1, PRICE1, 1);
		assertEquals(prod1, shoppingCart.getItem(0).getProduct());
	}
	
	@Test
	public void addSeveralItemsTest() {
		shoppingCart.addItem(prod1, PRICE1, 1);
		shoppingCart.addItem(prod2, PRICE2, 2);
		shoppingCart.addItem(prod3, PRICE3, 3);
		shoppingCart.addItem(prod4, PRICE4, 4);
		assertEquals(4, shoppingCart.getItems().size());
	}

	@Test
	public void changePriceItemTest() {
		shoppingCart.addItem(prod1, PRICE1, 1);
		shoppingCart.addItem(prod1, PRICE2, 2);
		assertEquals(PRICE2, shoppingCart.getItem(0).getPrice());
	}
	
	@Test
	public void removeProductTest() {
		shoppingCart.addItem(prod1, PRICE1, 1);
		shoppingCart.addItem(prod1, PRICE1, 1);
		shoppingCart.addItem(prod2, PRICE2, 2);
		assertTrue(shoppingCart.getItems().size() == 2);
		shoppingCart.removeItem(prod1);
		assertTrue(shoppingCart.getItems().size() == 1);
		assertEquals(prod2, shoppingCart.getItem(0).getProduct());
	}
	
	@Test
	public void removeInexistentProductTest() {
		shoppingCart.addItem(prod1, PRICE1, 1);
		shoppingCart.addItem(prod1, PRICE1, 1);
		shoppingCart.addItem(prod2, PRICE2, 2);
		Product inexistente = new Product(5L, "inexistent product");
		
		assertFalse(shoppingCart.removeItem(inexistente));
	}
	
	@Test
	public void removeProductByIndexText() {
		shoppingCart.addItem(prod1, PRICE1, 1);
		shoppingCart.addItem(prod2, PRICE2, 2);
		assertTrue(shoppingCart.getItems().size() == 2);
		shoppingCart.removeItem(2);
		assertTrue(shoppingCart.getItems().size() == 1);
		assertEquals(prod1, shoppingCart.getItem(0).getProduct());
	}
	
	@Test
	public void removeInexistentProductByIndexTest() {
		shoppingCart.addItem(prod1, PRICE1, 1);
		shoppingCart.addItem(prod2, PRICE2, 2);
		assertFalse(shoppingCart.removeItem(shoppingCart.getItems().size() + 1));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void cartTotalTest() {
		shoppingCart.addItem(prod1, PRICE1, 1);
		shoppingCart.addItem(prod2, PRICE2, 2);
		shoppingCart.addItem(prod3, PRICE3, 3);
		shoppingCart.addItem(prod4, PRICE4, 4);
		
		BigDecimal total = new BigDecimal(BigInteger.ZERO);
		total = total.add(PRICE1.multiply(new BigDecimal(1.0)));
		total = total.add(PRICE2.multiply(new BigDecimal(2.0)));
		total = total.add(PRICE3.multiply(new BigDecimal(3.0)));
		total = total.add(PRICE4.multiply(new BigDecimal(4.0)));
		BigDecimal totalCart = shoppingCart.getCartTotal();
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		totalCart = totalCart.setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals(total, totalCart);		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void cartTotalSum10Items2Test() {
		BigDecimal P1 = new BigDecimal(1);
		BigDecimal P2 = new BigDecimal(3);
		shoppingCart.addItem(prod1, P1, 1);
		shoppingCart.addItem(prod2, P2, 2);
		
		BigDecimal total = new BigDecimal(BigInteger.ZERO);
		total = total.add(P1.multiply(new BigDecimal(1.0)));
		total = total.add(P2.multiply(new BigDecimal(2.0)));
		BigDecimal totalCart = shoppingCart.getCartTotal();
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		totalCart = totalCart.setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals(total, totalCart);		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void cartTotalSum10Items3Test() {
		BigDecimal P1 = new BigDecimal(1);
		BigDecimal P2 = new BigDecimal(3);
		shoppingCart.addItem(prod1, P1, 1);
		shoppingCart.addItem(prod2, P2, 1);
		shoppingCart.addItem(prod3, P2, 1);
		
		BigDecimal total = new BigDecimal(BigInteger.ZERO);
		total = total.add(P1.multiply(new BigDecimal(1.0)));
		total = total.add(P2.multiply(new BigDecimal(2.0)));
		BigDecimal totalCart = shoppingCart.getCartTotal();
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		totalCart = totalCart.setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals(total, totalCart);		
	}
}
