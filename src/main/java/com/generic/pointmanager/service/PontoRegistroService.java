package com.generic.pointmanager.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.generic.pointmanager.models.entity.PontoRegistro;

public interface PontoRegistroService {
	
	ResponseEntity registrarPonto(Long id);
	
	ResponseEntity<List<PontoRegistro>> procurarPonto(BigInteger id);
	
	ResponseEntity<List<PontoRegistro>> procurarPontoEspecifico(Long idUsuario, Date dataInicial, Date dataFinal);

	ResponseEntity<List<PontoRegistro>> procurarPontoDia(Long idUsuario);
		
}
