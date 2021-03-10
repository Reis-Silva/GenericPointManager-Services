package com.citcolab.staffmanager.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citcolab.staffmanager.exception.validarEmailException;
import com.citcolab.staffmanager.exception.validarEmployerIdException;
import com.citcolab.staffmanager.models.entity.Usuario;
import com.citcolab.staffmanager.models.repository.UsuarioRepository;
import com.citcolab.staffmanager.service.UsuarioService;
import com.citcolab.staffmanager.ws.GerenciadorRepositoryService;

@Component
@RequestMapping("/usuario")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GerenciadorRepositoryService gerenciadorRepositoryService;

	
	@PostMapping("/cadastrar")
	public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario) {
		
		if(usuarioRepository.existsByEmail(usuario.getEmail())){
			throw new validarEmailException(usuario.getEmail(),"save");
		}
		
		Random random = new Random();
		Long numero = (long) random.nextInt(1000);
		
		if(usuarioRepository.existsByEmployerId(numero)){
			throw new validarEmployerIdException();
		}
		
		usuario.setEmployerId(numero);
		
		gerenciadorRepositoryService.persistirUsuario(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	
	@PostMapping("/atualizar")
	public ResponseEntity atualizarUsuario(@RequestBody Usuario usuario){
		
		validarEmail(usuario.getEmail())
		.map(usuarioExistente ->{
			usuarioExistente.setEmail(usuario.getEmail());
			usuarioExistente.setLocalOffice(usuario.getLocalOffice());
			usuarioExistente.setNome(usuario.getNome());
			usuarioExistente.setOffice(usuario.getOffice());
			usuarioExistente.setPhotoProfileUri(usuario.getPhotoProfileUri());
			usuarioExistente.setSenha(usuario.getSenha());
			gerenciadorRepositoryService.persistirUsuario(usuarioExistente);
			return usuarioExistente;
		}).orElseThrow( () -> new validarEmailException(usuario.getEmail(),"find"));
		
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/deletar")
	public ResponseEntity deletarUsuario(@RequestBody Usuario usuario){
		
		validarEmail(usuario.getEmail())
		.map(usuarioExistente ->{
			gerenciadorRepositoryService.deletarUsuario(usuarioExistente);
			return usuarioExistente;
		}).orElseThrow( () -> new validarEmailException(usuario.getEmail(),"find"));
		
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Optional<Usuario> validarEmail(String Email) {
		return usuarioRepository.findByEmail(Email);
	}
	
	
	
	

}
