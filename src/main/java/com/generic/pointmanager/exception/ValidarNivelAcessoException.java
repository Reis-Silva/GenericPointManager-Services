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
	
	public static void validarNivelAcesso(String nivelAcessoAdmin, String nivelAcessoUsuario) {
	
		if(nivelAcessoAdmin.equalsIgnoreCase("BAIXO")){
			throw new ValidarNivelAcessoException("Requer nivel de autorização MEDIO para este serviço");
		}
		
		if(nivelAcessoAdmin.equalsIgnoreCase("MEDIO") && nivelAcessoUsuario.equalsIgnoreCase("MEDIO")){
			throw new ValidarNivelAcessoException("Requer nivel de autorização ALTO para este serviço");
		}
		
	}

}
