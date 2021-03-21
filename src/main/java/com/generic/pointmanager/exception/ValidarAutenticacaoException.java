package com.generic.pointmanager.exception;

public class ValidarAutenticacaoException extends RuntimeException{

	private static final long serialVersionUID = -7438288681796790666L;
	
	public ValidarAutenticacaoException() {
		super("Email ou Senha inválido");
	}
	

}
