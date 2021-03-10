package com.citcolab.staffmanager.exception;

public class autenticacaoException extends RuntimeException{

	private static final long serialVersionUID = -7438288681796790666L;
	
	public autenticacaoException() {
		super("Email ou Senha inválido");
	}
	

}
