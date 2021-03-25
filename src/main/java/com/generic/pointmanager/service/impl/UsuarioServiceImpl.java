package com.generic.pointmanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generic.pointmanager.exception.ValidarAutenticacaoException;
import com.generic.pointmanager.exception.ValidarCpfException;
import com.generic.pointmanager.models.entity.Usuario;
import com.generic.pointmanager.models.repository.UsuarioRepository;
import com.generic.pointmanager.service.UsuarioService;
import com.generic.pointmanager.ws.GerenciadorRepositoryService;

@Component
@RequestMapping("/usuario")
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private GerenciadorRepositoryService gerenciadorRepositoryService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/autenticar")
	@Override
	public UserDetails autenticar(Usuario usuario) {
		
		ValidarCpfException.validarCPF(
	    		usuarioRepository.existsByCpf(usuario.getCpf()), "find");
		
		UserDetails userDetails = gerenciadorRepositoryService.autenticar(usuario);
		
		boolean autenticacaoSenha = passwordEncoder.matches(usuario.getSenha(), userDetails.getPassword());
		
		if(autenticacaoSenha) {
			return userDetails;
		}else {
			throw new ValidarAutenticacaoException();
		}	
	}

}
