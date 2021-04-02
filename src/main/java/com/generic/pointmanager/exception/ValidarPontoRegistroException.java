package com.generic.pointmanager.exception;

import java.util.List;

import org.springframework.stereotype.Component;

import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;
import com.generic.pointmanager.models.entity.PontoRegistro;

@Component
public class ValidarPontoRegistroException extends RuntimeException{

	private static final long serialVersionUID = -353206979796317214L;
	
	ValidarPontoRegistroException(String msg){
		super(msg);
	}
	
	ValidarPontoRegistroException(){
		
	}
	
	public static void validarPontoRegistroNumero(List<PontoRegistro> pontoRegistro, List<AtualizarPontoRegistro> atualizarPontoRegistro){
		
		
		for(int index=0;index<pontoRegistro.size();index++) {
			
		}
		
	}
}
