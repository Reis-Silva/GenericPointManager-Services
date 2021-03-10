package com.citcolab.staffmanager.exception.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citcolab.staffmanager.exception.validarEmailException;
import com.citcolab.staffmanager.models.repository.UsuarioRepository;

@Service
public class validarEmailExceptionImp {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	public void validarEmail(String email, String msg ) {
		
		boolean existe = usuarioRepository.existsByEmail(email);
		
		if (existe && msg.equalsIgnoreCase("save")) {
			throw new validarEmailException("Já existe usuario cadastrado com este email");
		}
		
		if(!existe && msg.equalsIgnoreCase("find")){
			throw new validarEmailException("Email de usuario não encontrado");
		}
		
	}
}
