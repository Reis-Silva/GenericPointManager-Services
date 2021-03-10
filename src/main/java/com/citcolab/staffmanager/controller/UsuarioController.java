package com.citcolab.staffmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.citcolab.staffmanager.models.entity.Usuario;
import com.citcolab.staffmanager.ws.ViewService;

@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private ViewService viewService;
	
	public ModelAndView cadastrar(Usuario usuario) {
		
		Usuario dadosUsuario = viewService.dadosUsuario(usuario);
		
		return null;
		
	}

}
