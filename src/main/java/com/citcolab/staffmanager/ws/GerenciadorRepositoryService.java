package com.citcolab.staffmanager.ws;

import com.citcolab.staffmanager.models.entity.Usuario;

public interface GerenciadorRepositoryService {
	
	void persistirUsuario(Usuario usuario);
	
	void deletarUsuario(Usuario usuario);
}
