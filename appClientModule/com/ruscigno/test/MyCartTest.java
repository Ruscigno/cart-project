/**
 * 
 */
package com.ruscigno.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.ruscigno.cart.MyCart;
import com.ruscigno.cart.Item;
import com.ruscigno.cart.Product;

/**
 * @author Sander
 *
 */
public class MyCartTest {

	private static final BigDecimal PRICE1 = new BigDecimal(591.97);
	private static final BigDecimal PRICE2 = new BigDecimal(394.77);
	private static final BigDecimal PRICE3 = new BigDecimal(144.07);
	private static final BigDecimal PRICE4 = new BigDecimal(27.9);

	private Product prod1;
	private Product prod2;
	private Product prod3;
	private Product prod4;
	
	private MyCart cart;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cart = new MyCart();
		
		prod1 = new Product(1L, "Java Senior Course");
		prod2 = new Product(2L, "Java Pleno Course");
		prod3 = new Product(3L, "Java Junior Course");
		prod4 = new Product(4L, "Java Basic Couse");
	}

	@Test
	public void addItemTest() {
		cart.addItem(prod1, PRICE1, 1);
		Item item = (Item) cart.getItens().toArray()[0];
		assertEquals(prod1, item.getProduct());
	}
	
	@Test
	public void addSeveralItemsTest() {
		cart.addItem(prod1, PRICE1, 1);
		cart.addItem(prod2, PRICE2, 2);
		cart.addItem(prod3, PRICE3, 3);
		cart.addItem(prod4, PRICE4, 4);
		assertEquals(4, cart.getItens().size());
	}

	@Test
	public void changePriceItemTest() {
		cart.addItem(prod1, PRICE1, 1);
		cart.addItem(prod1, PRICE2, 2);
		Item item = (Item) cart.getItens().toArray()[0];
		assertEquals(PRICE2, item.getPrice());
	}
	
	@Test
	public void removeProductTest() {
		cart.addItem(prod1, PRICE1, 1);
		cart.addItem(prod1, PRICE1, 1);
		cart.addItem(prod2, PRICE2, 2);
		assertTrue(cart.getItens().size() == 2);
		cart.removeItem(prod1);
		Item item = (Item) cart.getItens().toArray()[0];
		assertTrue(cart.getItens().size() == 1);
		assertEquals(prod2, item.getProduct());
	}
	
	@Test
	public void removeInexistentProductTest() {
		cart.addItem(prod1, PRICE1, 1);
		cart.addItem(prod1, PRICE1, 1);
		cart.addItem(prod2, PRICE2, 2);
		Product inexistente = new Product(5L, "inexistent product");
		
		assertFalse(cart.removeItem(inexistente));
	}
	
	@Test
	public void removeProductByIndexText() {
		cart.addItem(prod1, PRICE1, 1);
		cart.addItem(prod2, PRICE2, 2);
		assertTrue(cart.getItens().size() == 2);
		cart.removeItem(2);
		Item item = (Item) cart.getItens().toArray()[0];
		assertTrue(cart.getItens().size() == 1);
		assertEquals(prod1, item.getProduct());
	}
	
	@Test
	public void removeInexistentProductByIndexTest() {
		cart.addItem(prod1, PRICE1, 1);
		cart.addItem(prod2, PRICE2, 2);
		assertFalse(cart.removeItem(cart.getItens().size() + 1));
	}
	
	@Test
	public void cartTotalTest() {
		cart.addItem(prod1, PRICE1, 1);
		cart.addItem(prod2, PRICE2, 2);
		cart.addItem(prod3, PRICE3, 3);
		cart.addItem(prod4, PRICE4, 4);
		
		BigDecimal total = new BigDecimal(1925.32);
		BigDecimal totalCart = cart.getCartTotal();
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		totalCart = totalCart.setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals(total, totalCart);		
	}
}
