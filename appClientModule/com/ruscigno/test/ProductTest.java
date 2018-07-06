/**
 * 
 */
package com.ruscigno.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ruscigno.cart.Product;

/**
 * @author Sander
 *
 */
@RunWith(Parameterized.class)
public class ProductTest {

	private Product product;
	private Long id;
	private String description;

	public ProductTest(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> testConditions() {
		return Arrays.asList(new Object[][] { 
			{ 1L, "Java Senior Course" }, 
			{ 2L, "Java Pleno Course" },
			{ 3L, "Java Junior Course" }});

	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		product = new Product(id, description);
	}

	@Test
	public void sameDescriptionTest() {
		assertEquals(description, product.getDescription());
	}

	@Test
	public void descriptionNotEqualTest() {
		Product prod = new Product(id, description + id.toString());
		assertNotEquals(description, prod.getDescription());
	}

	@Test
	public void sameIdTest() {
		assertEquals(id, product.getID());
	}	
	
	@Test
	public void diferentIdTest() {
		Product prod = new Product(id * 2, description);
		assertNotEquals(id, prod.getID());
	}
}
