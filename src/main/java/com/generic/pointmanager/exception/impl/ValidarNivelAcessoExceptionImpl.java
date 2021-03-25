package com.generic.pointmanager.exception.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.generic.pointmanager.models.entity.Usuario;
import com.generic.pointmanager.models.repository.UsuarioRepository;

public class ValidarNivelAcessoExceptionImpl {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void validarNivelAcesso(String cpf) {
		
		Usuario usuario = usuarioRepository.findByCpf(cpf);
		
		if(usuario.getTipoNivelAcessoEnum().getDescricao().equalsIgnoreCase("baixo")){
			throw new ValidarNivelAcessoException("Nivel de autorização baixo para este serviço");
		}
		
		
	}

}
