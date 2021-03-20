package com.generic.pointmanager.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.generic.pointmanager.models.entity.Usuario;

public interface UsuarioService {
	
	ResponseEntity<Usuario> cadastrarUsuario(Usuario usuario);
	
	Optional<Usuario> procurarPorEmail(String Email);

}
