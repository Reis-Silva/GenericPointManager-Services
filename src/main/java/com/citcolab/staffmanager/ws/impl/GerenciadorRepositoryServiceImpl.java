package com.citcolab.staffmanager.ws.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.citcolab.staffmanager.models.entity.PontoRegistro;
import com.citcolab.staffmanager.models.entity.Usuario;
import com.citcolab.staffmanager.models.repository.PontoRegistroRepository;
import com.citcolab.staffmanager.models.repository.UsuarioRepository;
import com.citcolab.staffmanager.ws.GerenciadorRepositoryService;

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
		Usuario user = usuarioRepository.findByEmail(usuario.getEmail()).orElseThrow();
		
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
