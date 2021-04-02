package com.generic.pointmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.generic.pointmanager.models.entity.PontoRegistro;

public interface PontoRegistroService {
	
	ResponseEntity registrarPonto(Long id);
	
	ResponseEntity<List<PontoRegistro>> procurarPonto(Long id);
	
	ResponseEntity<List<PontoRegistro>> procurarPontoEspecifico(Long id, Date dataInicial, Date dataFinal);

	ResponseEntity<List<PontoRegistro>> procurarPontoDia(Long idUsuario);
	
		
}
