package com.generic.pointmanager.exception.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generic.pointmanager.exception.ValidarEmailException;
import com.generic.pointmanager.models.repository.UsuarioRepository;

@Service
public class ValidarEmailExceptionImp {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	public void validarEmail(String email, String msg ) {
		
		boolean existe = usuarioRepository.existsByEmail(email);
		
		if (existe && msg.equalsIgnoreCase("save")) {
			throw new ValidarEmailException("Já existe usuario cadastrado com este email");
		}
		
		if(!existe && msg.equalsIgnoreCase("find")){
			throw new ValidarEmailException("Email de usuario não encontrado");
		}
		
	}
}
