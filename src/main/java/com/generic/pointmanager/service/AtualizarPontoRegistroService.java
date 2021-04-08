package com.generic.pointmanager.service;

import org.springframework.http.ResponseEntity;

import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;
import com.generic.pointmanager.ws.impl.GetCollection;

public interface AtualizarPontoRegistroService {

	ResponseEntity solicitarAtualizarPonto(GetCollection collection);
	
	ResponseEntity listaAtualizarPonto(Long idAdmin);
	
	ResponseEntity listaAtualizarPontoEspecificoUsuario(Long idAdmin, String cpf);
	
	ResponseEntity atualizarPonto(Long idAdmin, Long idAtualizarPontoRegistro,
			AtualizarPontoRegistro atualizarPontoRegistro);

	ResponseEntity listaVisaoPontosDiariosConjuntoAtualizar(Long idAdmin, Long idAtualizarPontoRegistro);

}
