package com.ruscigno.cart;

public class Product {

    private Long id;
    private String description;

    /**
     * @param id
     * @param description
     */
    public Product(Long id, String description) {
    	this.id = id;
    	this.description = description;
    }

    /**
     * @return Long
     */
    public Long getID() {
		return id;
    }

    /**
     * @return String
     */
    public String getDescription() {
		return description;
    }
}