package com.citcolab.staffmanager.exception.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.citcolab.staffmanager.exception.validarDataException;
import com.citcolab.staffmanager.models.entity.PontoRegistro;

@Service
public class validarDataExceptionImpl{
	
	public void validarDataHora(Calendar data) {
		
		if(data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			throw new validarDataException("Dias não uteis");
		}
	}
	
	public void validarPontoDataHora(PontoRegistro pontoRegistro, List<PontoRegistro> pontoRegistros, Integer quantidadePontos) {
		
		if(pontoRegistro == null) {
			throw new validarDataException("Ponto de Registro não encontrado");
		}
		
		if(quantidadePontos == 4) {
			throw new validarDataException("Numero de pontos diários está completo");
		}
		
	}
	
	public void validarCronometro(Calendar dataComparacao, Date cronometro) {
		
		Calendar dataCronometro = Calendar.getInstance();
		dataCronometro.setTime(cronometro);
		
		Long hora = (long) dataComparacao.get(Calendar.HOUR_OF_DAY);
		Long minutos = (long) dataComparacao.get(Calendar.MINUTE);
		Long segundos = (long) dataComparacao.get(Calendar.SECOND);
		Long miligundos = (long) dataComparacao.get(Calendar.MILLISECOND);
		Long totalMilisegundos = (hora * 60 * 60 * 1000) + (minutos * 60 * 1000) + (segundos * 1000) + miligundos;
		
		Long horaCron = (long) dataCronometro.get(Calendar.HOUR_OF_DAY);
		Long minutosCron = (long) dataCronometro.get(Calendar.MINUTE);
		Long segundosCron = (long) dataCronometro.get(Calendar.SECOND);
		Long miligundosCron = (long) dataCronometro.get(Calendar.MILLISECOND);
		Long totalMilisegundosCron = (horaCron * 60 * 60 * 1000) + (minutosCron * 60 * 1000) + (segundosCron * 1000) + miligundosCron;
		
		Long cronPonto = totalMilisegundos - totalMilisegundosCron;
		
		if(cronPonto <= 300000) {
			throw new validarDataException("Só pode haver registro após 5 minutos em relação ao último");
		}
	}
	
	public void filtroData(Date dataInicial, Date dataFinal) {
		
		if(dataInicial.getTime() > dataFinal.getTime()) {
			throw new validarDataException("Data inicial não pode ser maior que data final");
		}
		
	}
}
