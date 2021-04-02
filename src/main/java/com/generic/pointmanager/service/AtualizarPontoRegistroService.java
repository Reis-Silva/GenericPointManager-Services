package com.generic.pointmanager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;
import com.generic.pointmanager.models.entity.PontoRegistro;
import com.generic.pointmanager.models.entity.Registro;

public interface AtualizarPontoRegistroService {

	ResponseEntity solicitarAtualizarPonto(List<AtualizarPontoRegistro> atualizarPontoRegistro, 
			List<PontoRegistro> pontoRegistro,Registro registro);
	
	ResponseEntity listaAtualizarPonto(Long idAdmin);
	
	ResponseEntity listaAtualizarPontoEspecifico(String cpf, Long idAdmin);
	
	ResponseEntity atualizarPonto(Long idAtualizarPontoRegistro, Long idAdmin,
			AtualizarPontoRegistro atualizarPontoRegistro);

}
