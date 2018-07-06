package com.ruscigno.exception;

public class ItemQuantityException extends RuntimeException {

	private static final String DEFAULT_MESSAGE = "The amount of items can not be less than zero";
	private static final long serialVersionUID = 1392066483516218964L;

	public ItemQuantityException() {
		super(DEFAULT_MESSAGE);
	}

}
