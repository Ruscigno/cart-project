package com.ruscigno.cart;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ruscigno.exception.ItemQuantityException;

public class MyCart {

	private List<Item> items = new ArrayList<>();

	/**
	 * @param product
	 * @param price
	 * @param quantity
	 * @throws ItemQuantityException
	 */
	public void addItem(Product product, BigDecimal price, int quantity)
			throws ItemQuantityException {
		
		item.forEach(item -> {
			if (item.getProduct().getID().equals(product.getID())) {
				item.addQuantity(quantity);
				item.setPrice(price);
				return;
			} else {
				addProduct(product, price, quantity);
				return;
			}
		})
		/*for (Item item : items) {
			if (item.getProduct().getID().equals(product.getID())) {
				item.addQuantity(quantity);
				item.setPrice(price);
				return;
			} else {
				addProduct(product, price, quantity);
				return;
			}
		}*/
		addProduct(product, price, quantity);
	}
	
	/**
	 * @param product
	 * @param price
	 * @param quantity
	 */
	private void addProduct(Product product, BigDecimal price, int quantity) {
		Item newItem = new Item(product, price, quantity);
		items.add(newItem);
	}

	/**
	 * @param product
	 * @return False if the product doesn't exist in the list or True insted 
	 */
	public boolean removeItem(Product product) {
		for (Item item : items) {
			if (item.getProduct().getID().equals(product.getID())) {
				items.remove(item);
				return true;
			}
		}
		return false;
	}

	/**
	 * @param index
	 * @return False if the product doesn't exist in the list or True insted
	 */
	public boolean removeItem(int index) {
		if (items.size() >= index) {
			items.remove(items.toArray()[index - 1]);
			return true;
		}else
			return false;
	}

	/**
	 * @return BigDecimal
	 */
	public BigDecimal getCartTotal() {
		BigDecimal total = new BigDecimal(BigInteger.ZERO,  2);
		
		for (Item item : items) {
			total = total.add(item.getTotalItem());
		}
		return total;
	}

	/**
	 * @return items
	 */
	public Collection<Item> getItens() {
		return items;
	}
}