package com.citcolab.staffmanager.exception;

public class validarAutenticacaoException extends RuntimeException{

	private static final long serialVersionUID = -7438288681796790666L;
	
	public validarAutenticacaoException() {
		super("Email ou Senha inválido");
	}
	

}
