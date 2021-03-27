package com.generic.pointmanager.service;

import org.springframework.http.ResponseEntity;

import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;

public interface AtualizarPontoRegistroService {

	ResponseEntity solicitarAtualizarPonto(Long idUsuario, Long idPontoRegistro, AtualizarPontoRegistro atualizarPontoRegistro);
	
	ResponseEntity listaAtualizarPonto(Long idUsuario, Long idPontoRegistro, AtualizarPontoRegistro atualizarPontoRegistro);
	
	ResponseEntity listaAtualizarPontoEspecifico(Long idUsuario, Long idPontoRegistro, AtualizarPontoRegistro atualizarPontoRegistro);
	
	ResponseEntity atualizarPonto(Long idUsuario, Long idPontoRegistro, AtualizarPontoRegistro atualizarPontoRegistro);
	
}
