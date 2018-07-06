/**
 * 
 */
package com.ruscigno.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ruscigno.cart.Item;
import com.ruscigno.cart.Product;
import com.ruscigno.exception.ItemQuantityException;

/**
 * @author Sander
 *
 */
@RunWith(Parameterized.class)
public class ItemTest {

	private Item item;
	private Product product;
	private BigDecimal valorUnitario;
	private int quantidade;

	private Long codigo;
	private String descricao;

	/**
	 * @param codigo
	 * @param descricao
	 * @param valorUnitario
	 * @param quantidade
	 */
	public ItemTest(Long codigo, String descricao, BigDecimal valorUnitario, int quantidade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> testConditions() {
		return Arrays
				.asList(new Object[][] { 
					{ 1L, "Curso Java Senior", new BigDecimal(591.97), 89 },
					{ 2L, "Curso Java Pleno", new BigDecimal(394.77), 52 },
					{ 3L, "Curso Java Junior", new BigDecimal(144.07), 279 },
					{ 4L, "Curso Java Estagiario", new BigDecimal(27.9), 55 } });

	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		product = new Product(codigo, descricao);
		item = new Item(product, valorUnitario, quantidade);
	}

	@Test
	public void productIgualTest() {
		assertEquals(product, item.getProduct());
	}

	@Test
	public void valorIgualUnitarioTest() {
		assertEquals(valorUnitario, item.getPrice());
	}

	@Test
	public void quantidadeIgualTest() {
		assertEquals(quantidade, item.getQuantity());
	}

	@Test
	public void productDiferenteTest() {
		assertNotEquals(product, new Product(codigo, descricao));
	}

	@Test
	public void valorDiferenteUnitarioTest() {
		Item it = new Item(product, valorUnitario.multiply(valorUnitario), quantidade);
		assertNotEquals(valorUnitario, it.getPrice());
	}

	@Test
	public void quantidadeDiferenteTest() {
		Item it = new Item(product, valorUnitario, quantidade * 2);
		assertNotEquals(quantidade, it.getQuantity());
	}

	@Test
	public void addQuantidadeTest() {
		Random rand = new Random();
		int randInt = rand.nextInt(100);

		item.addQuantity(randInt);
		assertEquals(quantidade + randInt, item.getQuantity());
	}

	@Test
	public void addMultQuantidadeTest() {
		Random rand = new Random();
		int randInt;
		int total = 0;

		for (int i = 0; i < 20; i++) {
			randInt = rand.nextInt(100);
			item.addQuantity(randInt);
			total += randInt;
		}
		assertEquals(quantidade + total, item.getQuantity());
	}

	@Test
	public void removeQuantidadeTest() {
		Random rand = new Random();
		int randInt = rand.nextInt(item.getQuantity() - 1) * -1;

		item.addQuantity(randInt);
		assertEquals(quantidade + randInt, item.getQuantity());
	}

	@Test
	public void removeMultQuantidadeTest() {
		int qtd = item.getQuantity();

		for (int i = 0; i < qtd; i++) {
			item.removeQuantity(1);
		}
		assertEquals(0, item.getQuantity());
	}

	@Test(expected = ItemQuantityException.class)
	public void removeAcimaDaQuantidadeTest() {
		item.removeQuantity(item.getQuantity() + 1);
		assertEquals(-1, item.getQuantity());
	}

	@Test
	public void valorTotalTest() {
		Random rand = new Random();
		int randInt;
		int total = 0;

		for (int i = 0; i < 20; i++) {
			randInt = rand.nextInt(100);
			item.addQuantity(randInt);
			total += randInt;
		}
		assertEquals(valorUnitario.multiply(new BigDecimal(quantidade + total)), item.getTotalItem());
	}	
}
