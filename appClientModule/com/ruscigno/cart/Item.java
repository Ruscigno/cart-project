package com.ruscigno.cart;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.ruscigno.exception.ItemQuantityException;

public class Item {

    private static final int MIN_QUANTITY = 0;
	private Product product;
    private BigDecimal price = new BigDecimal(BigInteger.ZERO,  2);
    private int quantity;

    /**
	 * @param price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	private boolean checkQuantity(int newQuantity) throws ItemQuantityException {
		if (newQuantity < MIN_QUANTITY) {
			throw new ItemQuantityException();
		}
		return true;
	}
	
	/**
	 * @param quantity
	 * @throws ItemQuantityException 
	 */
	public void addQuantity(int quantity) throws ItemQuantityException {
		int qtd = this.quantity + quantity;
		checkQuantity(qtd);
		this.quantity  = qtd;
	}
	
	/**
	 * @param quantity
	 * @throws ItemQuantityException 
	 */
	public void removeQuantity(int quantity) throws ItemQuantityException {
		addQuantity(quantity * -1);
	}

	/**
     * @param product
     * @param price
     * @param quantity
     */
    public Item(Product product, BigDecimal price, int quantity) {
    	this.product = product;
    	this.price = price;
    	this.quantity = quantity;
    }

    /**
     * @return product
     */
    public Product getProduct() {
    	return product;
    }

    /**
     * @return BigDecimal
     */
    public BigDecimal getPrice() {
    	return price;
    }

    /**
     * @return int
     */
    public int getQuantity() {
    	return quantity;
    }

    /**
     * @return BigDecimal
     * @throws ItemQuantityException 
     */
    public BigDecimal getTotalItem() throws ItemQuantityException {
    	checkQuantity(quantity);
    	return price.multiply(new BigDecimal(quantity));
    }
}
