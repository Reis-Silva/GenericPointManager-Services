package com.citcolab.staffmanager.exception;

public class validarEmployerIdException extends RuntimeException{

	private static final long serialVersionUID = -8822110611821226571L;
	
	public validarEmployerIdException() {
		super("O numero EmployerId já é existente");
	}
	
}
