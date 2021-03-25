package com.generic.pointmanager.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.generic.pointmanager.models.repository.UsuarioRepository;

@Component
public class ValidarCpfException extends RuntimeException{

	private static final long serialVersionUID = 4103035993698047867L;
	
	@Autowired
	public static UsuarioRepository usuarioRepository;

	public ValidarCpfException(String msg) {
		super(msg);
	}
	
	public ValidarCpfException() {

	}

	public static void validarCPF(boolean cpf, String msg) {
		
		boolean existeCPF = cpf;
		
		if (existeCPF && msg.equalsIgnoreCase("save")) {
			throw new ValidarCpfException("Já existe um usuario cadastrado com este CPF");
		}
		
		if(!existeCPF && msg.equalsIgnoreCase("find")){
			throw new ValidarCpfException("usuario não encontrado");
		}

	}
	
}
