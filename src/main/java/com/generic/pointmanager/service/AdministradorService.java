package com.generic.pointmanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.generic.pointmanager.models.entity.Usuario;

public interface AdministradorService {
	
	ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario, @PathVariable("email") Long IdAdmin);
	
	ResponseEntity atualizarUsuario(@RequestBody Usuario usuario, @PathVariable("email") Long IdAdmin);
	
	ResponseEntity deletarUsuario(@PathVariable("cpf") String cpf, @PathVariable("idAdmin") Long idAdmin);

}
