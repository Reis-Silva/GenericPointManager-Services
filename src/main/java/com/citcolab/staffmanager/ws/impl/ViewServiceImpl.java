package com.citcolab.staffmanager.ws.impl;

import com.citcolab.staffmanager.models.entity.Usuario;
import com.citcolab.staffmanager.ws.ViewService;

public class ViewServiceImpl implements ViewService{

	
	@Override
	public Usuario dadosUsuario(Usuario usuario) {
		
		Usuario dadosUsuario = new Usuario();
		
		dadosUsuario.setSenha(usuario.getSenha());
		dadosUsuario.setPhotoProfileUri(usuario.getPhotoProfileUri());
		dadosUsuario.setNome(usuario.getNome());
		dadosUsuario.setCpf(usuario.getCpf());
		dadosUsuario.setLocalOffice(usuario.getLocalOffice());
		dadosUsuario.setEmployerId(usuario.getEmployerId());
		dadosUsuario.setEmail(usuario.getEmail());
		dadosUsuario.setOffice(usuario.getOffice());
		
		return dadosUsuario;
	}

}
