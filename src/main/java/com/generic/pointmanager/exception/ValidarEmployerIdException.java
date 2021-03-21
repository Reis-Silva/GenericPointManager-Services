package com.generic.pointmanager.exception;

public class ValidarEmployerIdException extends RuntimeException{

	private static final long serialVersionUID = -8822110611821226571L;
	
	public ValidarEmployerIdException() {
		super("O numero EmployerId já é existente");
	}
	
}
