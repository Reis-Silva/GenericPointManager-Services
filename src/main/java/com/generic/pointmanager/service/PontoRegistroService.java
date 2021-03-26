package com.generic.pointmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.generic.pointmanager.models.entity.PontoRegistro;

public interface PontoRegistroService {
	
	ResponseEntity registrarPonto(@PathVariable Long id);
	
	ResponseEntity<List<PontoRegistro>> procurarPonto(@PathVariable("id") Long id);
	
	ResponseEntity<List<PontoRegistro>> procurarPontoEspecifico(@PathVariable("id") Long id,@PathVariable Date dataInicial, @PathVariable Date dataFinal);
	
		
}
