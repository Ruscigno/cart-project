package com.ruscigno.cart;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class MyCartFactory {

	private Map<String, MyCart> carts = new HashMap<>();
	
	/**
	 * @return the carts
	 */
	public Map<String, MyCart> getCarts() {
		return carts;
	}

	public MyCartFactory() {
	}

    /**
     * @param clientID
     * @return CarrinhoCompras
     */
    public MyCart create(String clientID) {
		for (String key : carts.keySet()) {
			if (key.equals(clientID)){
				return carts.get(key);
			}
		}
		MyCart newCart = new MyCart();
		carts.put(clientID, newCart);
		return newCart;
    }

    /**
     * @return BigDecimal
     */
    public BigDecimal getAverageTicketValue() {
    	MyCart cart;
    	BigDecimal total = new BigDecimal(BigInteger.ZERO,  2);
    	
		for (String key : carts.keySet()) {
			cart = carts.get(key);
			total = total.add(cart.getCartTotal());
		}
		total = total.divide(new BigDecimal(carts.size()));
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		return total;
    }

    /**
     * @param clientID
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidate(String clientID) {
		for (String key : carts.keySet()) {
			if (key.equals(clientID)){
				carts.remove(key);
				return true;
			}
		}
		return false;
    }
}
