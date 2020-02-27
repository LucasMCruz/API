package com.example.demo.exceptions;

public class AutorNao extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6904390836155115853L;
	public AutorNao(String mensagem) {
		super(mensagem);
	}
	public AutorNao(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
