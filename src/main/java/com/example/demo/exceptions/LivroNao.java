package com.example.demo.exceptions;

public class LivroNao extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6904390836155115853L;
	public LivroNao(String mensagem) {
		super(mensagem);
	}
	public LivroNao(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
