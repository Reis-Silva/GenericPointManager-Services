package com.generic.pointmanager.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generic.pointmanager.exception.ValidarEmployerIdException;
import com.generic.pointmanager.exception.impl.ValidarCpfExceptionImpl;
import com.generic.pointmanager.exception.impl.ValidarNivelAcessoException;
import com.generic.pointmanager.models.entity.Usuario;
import com.generic.pointmanager.models.repository.UsuarioRepository;
import com.generic.pointmanager.service.AdministradorService;
import com.generic.pointmanager.ws.GerenciadorRepositoryService;

@Component
@RestController
@RequestMapping("/administrador")
public class AdministradorServiceImpl implements AdministradorService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	@Autowired
	private GerenciadorRepositoryService gerenciadorRepositoryService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ValidarCpfExceptionImpl validarCpfExceptionImpl;
	
	@Autowired
	private ValidarNivelAcessoException validarNivelAcessoException;
	
	@PostMapping("/cadastrar/{idAdmin}")
	@Override
	public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario, @PathVariable("idAdmin") String idAdmin) {
		
	    validarCpfExceptionImpl.validarCPF(usuario.getCpf(), "save");
		
		Random random = new Random();
		Long numero = (long) random.nextInt(1000);
		
		if(usuarioRepository.existsByEmployerId(numero)){
			throw new ValidarEmployerIdException();
		}
		
		usuario.setEmployerId(numero);
		
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		
		validarNivelAcessoException.teste("teste");
		
		gerenciadorRepositoryService.persistirUsuario(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/atualizar/{idAdmin}")
	@Override
	public ResponseEntity atualizarUsuario(@RequestBody Usuario usuario, @PathVariable("idAdmin") String idAdmin){
		
		validarCpfExceptionImpl.validarCPF(usuario.getCpf(), "find");
		
		Usuario atualizarUsuario = usuarioRepository.findByCpf(usuario.getCpf());
		atualizarUsuario.setEmail(usuario.getEmail());
		atualizarUsuario.setLocalOffice(usuario.getLocalOffice());
		atualizarUsuario.setNome(usuario.getNome());
		atualizarUsuario.setOffice(usuario.getOffice());
		atualizarUsuario.setPhotoProfileUri(usuario.getPhotoProfileUri());
		atualizarUsuario.setSenha(usuario.getSenha());
		
		gerenciadorRepositoryService.persistirUsuario(atualizarUsuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/deletar//{idAdmin}/{cpf}")
	@Override
	public ResponseEntity deletarUsuario(@PathVariable("cpf") String cpf, @PathVariable("idAdmin") String idAdmin){
		
		validarCpfExceptionImpl.validarCPF(cpf, "find");
		
		Usuario deletarUsuario = usuarioRepository.findByCpf(cpf);
		
		gerenciadorRepositoryService.deletarUsuario(deletarUsuario);
		
		return ResponseEntity.noContent().build();
	}

}
