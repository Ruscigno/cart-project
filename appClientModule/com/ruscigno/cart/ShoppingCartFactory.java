package com.ruscigno.cart;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartFactory {

	private Map<String, ShoppingCart> shoppingCarts = new HashMap<>();
	
	/**
	 * @return the carts
	 */
	public Map<String, ShoppingCart> getCarts() {
		return shoppingCarts;
	}

	public ShoppingCartFactory() {
	}

    /**
     * @param clientID
     * @return CarrinhoCompras
     */
    public ShoppingCart create(String clientID) {
		for (String key : shoppingCarts.keySet()) {
			if (key.equals(clientID)){
				return shoppingCarts.get(key);
			}
		}
		ShoppingCart newCart = new ShoppingCart();
		shoppingCarts.put(clientID, newCart);
		return newCart;
    }

    /**
     * @return BigDecimal
     */
    @SuppressWarnings("deprecation")
	public BigDecimal getAverageTicketValue() {
    	return (shoppingCarts.keySet().stream().map(key -> shoppingCarts.get(key).getCartTotal()).
    			reduce(new BigDecimal(BigInteger.ZERO), (x, y) -> x.add(y))).
    			divide(new BigDecimal(shoppingCarts.size())).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @param customerID Customer ID to be verified
     * @return boolean returns TRUE if the customer passed as a parameter has a Shopping Cart
     */
    public boolean remove(String customerID) {
    	return (shoppingCarts.remove(customerID) != null);
    }
}
