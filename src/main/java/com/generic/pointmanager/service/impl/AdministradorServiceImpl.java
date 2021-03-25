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

import com.generic.pointmanager.exception.ValidarCpfException;
import com.generic.pointmanager.exception.ValidarEmployerIdException;
import com.generic.pointmanager.exception.ValidarNivelAcessoException;
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
	
	@PostMapping("/cadastrar/{idAdmin}")
	@Override
	public ResponseEntity cadastrarUsuario(@RequestBody Usuario usuario, @PathVariable("idAdmin") String idAdmin) {
		
	    ValidarCpfException.validarCPF(
	    		usuarioRepository.existsByCpf(usuario.getCpf()), "save");
	    
	    ValidarNivelAcessoException.validarNivelAcesso(
	    		usuarioRepository.findByCpf(idAdmin).getNivelAcesso().getDescricao());
		
		Random random = new Random();
		Long numero = (long) random.nextInt(1000);
		
		if(usuarioRepository.existsByEmployerId(numero)){
			throw new ValidarEmployerIdException();
		}
		
		usuario.setEmployerId(numero);
		
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		
		//gerenciadorRepositoryService.persistirUsuario(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/atualizar/{idAdmin}")
	@Override
	public ResponseEntity atualizarUsuario(@RequestBody Usuario usuario, @PathVariable("idAdmin") String idAdmin){
		
		ValidarCpfException.validarCPF(
	    		usuarioRepository.existsByCpf(usuario.getCpf()), "find");
		
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
		
		ValidarCpfException.validarCPF(
	    		usuarioRepository.existsByCpf(cpf), "find");
		
		Usuario deletarUsuario = usuarioRepository.findByCpf(cpf);
		
		gerenciadorRepositoryService.deletarUsuario(deletarUsuario);
		
		return ResponseEntity.noContent().build();
	}

}
