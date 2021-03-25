package com.generic.pointmanager.ws.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.generic.pointmanager.models.entity.PontoRegistro;
import com.generic.pointmanager.models.entity.Usuario;
import com.generic.pointmanager.models.repository.PontoRegistroRepository;
import com.generic.pointmanager.models.repository.UsuarioRepository;
import com.generic.pointmanager.ws.GerenciadorRepositoryService;

@Service
public class GerenciadorRepositoryServiceImpl implements GerenciadorRepositoryService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PontoRegistroRepository pontoRegistroRepository;
	
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

	@Override
	public UserDetails autenticar(Usuario usuario) {
		Usuario user = usuarioRepository.findByCpf(usuario.getCpf());
		
		return User
				.builder()
				.username(user.getEmail())
				.password(user.getSenha())
				.build();
	}
	
	@Override
	@Transactional
	public void persistirRegistroRepository(PontoRegistro pontoRegistro) {
		pontoRegistroRepository.save(pontoRegistro);
	}

}
