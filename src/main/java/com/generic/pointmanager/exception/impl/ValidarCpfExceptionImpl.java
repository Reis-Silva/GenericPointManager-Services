package com.generic.pointmanager.exception.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generic.pointmanager.exception.ValidarCpfException;
import com.generic.pointmanager.models.repository.UsuarioRepository;

@Service
public class ValidarCpfExceptionImpl {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	public void validarCPF(String cpf, String msg ) {
		
		boolean existeCPF = usuarioRepository.existsByEmail("cpf");
		
		if (existeCPF && msg.equalsIgnoreCase("save")) {
			throw new ValidarCpfException("Já existe usuario cadastrado com este CPF");
		}
		
		if(!existeCPF && msg.equalsIgnoreCase("find")){
			throw new ValidarCpfException("CPF de usuario não encontrado");
		}
				
	}
}
