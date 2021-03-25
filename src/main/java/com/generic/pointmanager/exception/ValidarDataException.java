package com.generic.pointmanager.exception;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.generic.pointmanager.models.entity.PontoRegistro;

@Component
public class ValidarDataException extends RuntimeException{

	private static final long serialVersionUID = 3054527412432131539L;

	public ValidarDataException(String msg) {
		super(msg);
	}
	
	public ValidarDataException() {

	}
	
	public static void validarDataHora(Calendar data) {
		
		if(data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			throw new ValidarDataException("Dias não uteis");
		}
	}
	
	public static void validarPontoDataHora(PontoRegistro pontoRegistro, List<PontoRegistro> pontoRegistros, Integer quantidadePontos) {
		
		if(pontoRegistro == null) {
			throw new ValidarDataException("Ponto de Registro não encontrado");
		}
		
		if(quantidadePontos == 4) {
			throw new ValidarDataException("Numero de pontos diários está completo");
		}
		
	}
	
	public static void validarCronometro(Calendar dataComparacao, Date cronometro) {
		
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
			throw new ValidarDataException("Só pode haver registro após 5 minutos em relação ao último");
		}
	}
	
	public static void filtroData(Date dataInicial, Date dataFinal) {
		
		if(dataInicial.getTime() > dataFinal.getTime()) {
			throw new ValidarDataException("Data inicial não pode ser maior que data final");
		}
		
	}
}
