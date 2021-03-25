package com.generic.pointmanager.exception.impl;

public class ValidarNivelAcessoException extends RuntimeException{

	private static final long serialVersionUID = 9138713614423558377L;
	
	public ValidarNivelAcessoException(String msg){
		super(msg);
	}
	
	public void teste(String teste) {
		new ValidarNivelAcessoException(teste);
	}

}
