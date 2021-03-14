package com.citcolab.staffmanager.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.citcolab.staffmanager.models.entity.Usuario;

public interface UsuarioService {
	
	ResponseEntity<Usuario> cadastrarUsuario(Usuario usuario);
	
	Optional<Usuario> procurarPorEmail(String Email);

}
