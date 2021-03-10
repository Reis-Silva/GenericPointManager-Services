package com.citcolab.staffmanager.ws.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citcolab.staffmanager.models.entity.Usuario;
import com.citcolab.staffmanager.models.repository.UsuarioRepository;
import com.citcolab.staffmanager.ws.GerenciadorRepositoryService;

@Service
public class GerenciadorRepositoryServiceImpl implements GerenciadorRepositoryService{

	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public void persistirUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void deletarUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

}
