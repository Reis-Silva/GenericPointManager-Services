package com.generic.pointmanager.ws.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;
import com.generic.pointmanager.models.entity.PontoRegistro;
import com.generic.pointmanager.models.entity.Registro;
import com.generic.pointmanager.models.entity.Usuario;
import com.generic.pointmanager.models.repository.AtualizarPontoRegistroRepository;
import com.generic.pointmanager.models.repository.PontoRegistroRepository;
import com.generic.pointmanager.models.repository.RegistroRepository;
import com.generic.pointmanager.models.repository.UsuarioRepository;
import com.generic.pointmanager.ws.GerenciadorRepositoryService;

@Service
public class GerenciadorRepositoryServiceImpl implements GerenciadorRepositoryService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PontoRegistroRepository pontoRegistroRepository;
	
	@Autowired
	private AtualizarPontoRegistroRepository atualizarPontoRegistroRepository;
	
	private RegistroRepository registroRepository;
	
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
	public void persistirPontoRegistro(PontoRegistro pontoRegistro) {
		pontoRegistroRepository.save(pontoRegistro);
	}

	@Override
	@Transactional
	public void persistirAtualizarPontoRegistro(AtualizarPontoRegistro atualizarPontoRegistro) {
		atualizarPontoRegistroRepository.save(atualizarPontoRegistro);
	}

	@Override
	@Transactional
	public void persitirRegistro(Registro registro) {
		registroRepository.save(registro);
	}

}
