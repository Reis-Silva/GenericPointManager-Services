package com.generic.pointmanager.exception;

import org.springframework.stereotype.Component;

@Component
public class ValidarNivelAcessoException extends RuntimeException{

	private static final long serialVersionUID = 9138713614423558377L;
	
	public ValidarNivelAcessoException(String msg){
		super(msg);
	}
	
	public ValidarNivelAcessoException(){
		
	}
	
	public static void validarNivelAcesso(String nivelAcesso) {
	
		if(nivelAcesso.equalsIgnoreCase("BAIXO")){
			throw new ValidarNivelAcessoException("Nivel de autorização baixo para este serviço");
		}
		
	}

}
