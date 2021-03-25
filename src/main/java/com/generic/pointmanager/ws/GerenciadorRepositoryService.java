package com.generic.pointmanager.ws;

import org.springframework.security.core.userdetails.UserDetails;

import com.generic.pointmanager.models.entity.PontoRegistro;
import com.generic.pointmanager.models.entity.Usuario;

public interface GerenciadorRepositoryService {
	
	void persistirUsuario(Usuario usuario);
	
	void deletarUsuario(Usuario usuario);
	
	UserDetails autenticar(Usuario usuario);
	
	public void persistirRegistroRepository(PontoRegistro pontoRegistro);
	
}