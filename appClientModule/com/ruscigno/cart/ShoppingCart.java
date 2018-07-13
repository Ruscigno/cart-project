package com.ruscigno.cart;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ruscigno.exception.ItemQuantityException;

public class ShoppingCart {

	private List<Item> items = new ArrayList<>();

	/**
	 * @param product
	 * @param price
	 * @param quantity
	 * @throws ItemQuantityException
	 */
	public void addItem(Product product, BigDecimal price, int quantity) throws ItemQuantityException {
		if (items.stream().filter(item -> item.equals(product.getID())).map(item -> item.update(price, quantity))
				.count() == 0) {
			items.add(new Item(product, price, quantity));
		}
//		items.stream().filter(item -> item.equals(product.getID())).map(item -> item.update(price, quantity)).
//			filter(item -> item == null).map(item -> items.add(new Item(product, price, quantity)));
	}

	/**
	 * @param product
	 * @return False if the product doesn't exist in the list or True insted
	 */
	public boolean removeItem(Product product) {
		for (Item item : items) {
			if (item.getProduct().getID().equals(product.getID())) {
				return items.remove(item);
			}
		}
		return false;
	}

	/**
	 * @param index
	 * @return False if the product doesn't exist in the list or True insted
	 */
	public boolean removeItem(int index) {
		if (items.size() < index)
			return false;
		
		items.remove(getItem(index - 1));
		return true;
	}

	/**
	 * @return BigDecimal
	 */
	public BigDecimal getCartTotal() {
		return items.stream().map(Item::getTotalItem).reduce(new BigDecimal(BigInteger.ZERO), (x, y) -> x.add(y));
	}

	/**
	 * @return items
	 */
	public Collection<Item> getItems() {
		return items;
	}
	
	public Item getItem(int index) {
		return items.get(index);
	}
}