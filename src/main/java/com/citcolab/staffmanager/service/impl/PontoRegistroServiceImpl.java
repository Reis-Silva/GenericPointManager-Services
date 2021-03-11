package com.citcolab.staffmanager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citcolab.staffmanager.exception.impl.validarDataExceptionImpl;
import com.citcolab.staffmanager.models.entity.PontoRegistro;
import com.citcolab.staffmanager.service.PontoRegistroService;

@Service
@RequestMapping("/pontoregistro")
public class PontoRegistroServiceImpl implements PontoRegistroService{
	
	private validarDataExceptionImpl validarDataExceptionImpl;
	
	@PostMapping("/registrar")
	public ResponseEntity registrarPonto(@RequestBody PontoRegistro pontoRegistro) {
		
		Calendar data = Calendar.getInstance();
		
		validarDataExceptionImpl.validarDataHora(data);
		
		SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm:ss");
		
		pontoRegistro.setDataPonto(formatarData.format(data.getTime()));
		
		pontoRegistro.setHoraPonto(formatarHora.format(data.getTime()));
		
		pontoRegistro.setLocalPonto("fajuta");
		
		return ResponseEntity.ok(pontoRegistro);
	}
	
	
}