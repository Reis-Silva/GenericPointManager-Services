package com.citcolab.staffmanager.ws;

import org.springframework.security.core.userdetails.UserDetails;

import com.citcolab.staffmanager.models.entity.PontoRegistro;
import com.citcolab.staffmanager.models.entity.Usuario;

public interface GerenciadorRepositoryService {
	
	void persistirUsuario(Usuario usuario);
	
	void deletarUsuario(Usuario usuario);
	
	UserDetails autenticar(Usuario usuario);
	
	public void persistirRegistroRepository(PontoRegistro pontoRegistro);
	
}
