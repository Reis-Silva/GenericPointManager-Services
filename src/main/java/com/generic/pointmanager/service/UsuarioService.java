package com.generic.pointmanager.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.generic.pointmanager.models.entity.Usuario;

public interface UsuarioService {
	
	UserDetails autenticar(Usuario usuario);

}
