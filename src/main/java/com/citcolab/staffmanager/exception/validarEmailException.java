package com.citcolab.staffmanager.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citcolab.staffmanager.models.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class validarEmailException extends RuntimeException{

	private static final long serialVersionUID = 4103035993698047867L;
	
	@Autowired
	public static UsuarioRepository usuarioRepository;
	
	public validarEmailException(String msg, String email) {
		super(validarEmail(msg, email));
	}
	
	public static String validarEmail(String msg, String email) {
		boolean existe = usuarioRepository.existsByEmail(email);
		
		if (existe && msg.equalsIgnoreCase("save")) {
			throw new validarEmailException("Já existe usuario cadastrado com este email", msg);
		}
		
		if(!existe && msg.equalsIgnoreCase("find")){
			throw new validarEmailException("Email de usuario não encontrado", msg);
		}
		return null;

	}

}
