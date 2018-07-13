/**
 * 
 */
package com.ruscigno.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.ruscigno.cart.ShoppingCart;
import com.ruscigno.cart.ShoppingCartFactory;
import com.ruscigno.cart.Product;

/**
 * @author Sander
 *
 */
public class ShoppingCartFactoryTest {

	private static final String VIP_CLIENT = "Sander Ruscigno";
	private static final String CLIENT = "John Silva";
	
	private static final BigDecimal PRICE1 = new BigDecimal(897.974);
	private static final BigDecimal PRICE2 = new BigDecimal(632.773);
	private static final BigDecimal PRICE3 = new BigDecimal(289.073);
	private static final BigDecimal PRICE4 = new BigDecimal(99.93);
	private static final BigDecimal PRICE5 = new BigDecimal(897.973);
	private Product prod1;
	private Product prod2;
	private Product prod3;
	private Product prod4;
	private ShoppingCartFactory cartFactory;
	private ShoppingCart cartVip;
	private ShoppingCart cartNormal;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		prod1 = new Product(1L, "Java Senior Course");
		prod2 = new Product(2L, "Java Pleno Course");
		prod3 = new Product(3L, "Java Junior Course");
		prod4 = new Product(4L, "Java Basic Course");
		
		cartFactory = new ShoppingCartFactory();
		cartVip = cartFactory.create(VIP_CLIENT);
		cartNormal = cartFactory.create(CLIENT);
	}

	@Test
	public void createTest() {
		cartFactory.getCarts().clear();
		ShoppingCart shoppingCart = cartFactory.create(VIP_CLIENT);
		assertEquals(shoppingCart, cartFactory.create(VIP_CLIENT));
	}
	
	@Test
	public void createTwoCartsTest() {
		cartFactory.getCarts().clear();
		ShoppingCart cart1 = cartFactory.create(VIP_CLIENT);
		ShoppingCart cart2 = cartFactory.create(CLIENT);
		assertEquals(cart1, cartFactory.create(VIP_CLIENT));
		assertEquals(cart2, cartFactory.create(CLIENT));
	}
	
	@Test
	public void checkQuantityTest() {
		cartFactory.getCarts().clear();
		cartFactory.create(VIP_CLIENT);
		cartFactory.create(CLIENT);
		
		assertTrue(cartFactory.getCarts().size() == 2);
	}
	
	@Test
	public void removeCartTest() {
		cartFactory.getCarts().clear();
		cartFactory.create(VIP_CLIENT);
		cartFactory.create(CLIENT);
		
		assertTrue(cartFactory.getCarts().size() == 2);
		assertTrue(cartFactory.remove(VIP_CLIENT));
		assertTrue(cartFactory.getCarts().size() == 1);
		assertFalse(cartFactory.remove(VIP_CLIENT));
		assertTrue(cartFactory.getCarts().size() == 1);
		assertTrue(cartFactory.remove(CLIENT));
		assertTrue(cartFactory.getCarts().size() == 0);
	}
	
	@Test
	public void removeUndefinedCartTest() {
		cartFactory.getCarts().clear();
		cartFactory.create(VIP_CLIENT);
		cartFactory.create(CLIENT);
		
		assertFalse(cartFactory.remove("Inexistent cart"));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void averageTicketValueTest() {
		cartFactory.getCarts().clear();
		cartVip = cartFactory.create(VIP_CLIENT);
		cartNormal = cartFactory.create(CLIENT);
		
		Random rand = new Random();

		cartVip.addItem(prod1, PRICE1, rand.nextInt(10));
		cartVip.addItem(prod2, PRICE2, rand.nextInt(10));
		cartNormal.addItem(prod3, PRICE3, rand.nextInt(10));
		cartNormal.addItem(prod4, PRICE4, rand.nextInt(10));		
		
		BigDecimal total = cartVip.getCartTotal().add(cartNormal.getCartTotal());
		total = total.divide(new BigDecimal(2));
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals(total, cartFactory.getAverageTicketValue());
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void roundUpTest() {
		cartFactory.getCarts().clear();
		cartVip = cartFactory.create(VIP_CLIENT);
		cartNormal = cartFactory.create(CLIENT);
		
		cartVip.addItem(prod1, PRICE1, 1);
		cartVip.addItem(prod2, PRICE2, 1);
		cartNormal.addItem(prod3, PRICE3, 1);
		cartNormal.addItem(prod4, PRICE4, 1);
		
		BigDecimal total = new BigDecimal(959.88);
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals(total, cartFactory.getAverageTicketValue());
	}	
	
	@SuppressWarnings("deprecation")
	@Test
	public void roundDownTest() {
		cartFactory.getCarts().clear();
		cartVip = cartFactory.create(VIP_CLIENT);
		cartNormal = cartFactory.create(CLIENT);
		
		cartVip.addItem(prod1, PRICE5, 1);
		cartVip.addItem(prod2, PRICE2, 1);
		cartNormal.addItem(prod3, PRICE3, 1);
		cartNormal.addItem(prod4, PRICE4, 1);
		
		BigDecimal total = new BigDecimal(959.87);
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		assertEquals(total, cartFactory.getAverageTicketValue());
	}	

}
