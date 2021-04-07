package com.generic.pointmanager.exception;

import java.util.List;

import org.springframework.stereotype.Component;

import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;
import com.generic.pointmanager.models.entity.PontoRegistro;

@Component
public class ValidarPontoRegistroException extends RuntimeException {

	private static final long serialVersionUID = -353206979796317214L;

	ValidarPontoRegistroException(String msg) {
		super(msg);
	}

	ValidarPontoRegistroException() {

	}

	public static void validarPontoRegistroNumero(PontoRegistro[] pontoRegistros, AtualizarPontoRegistro[] atualizarPontoRegistros) {

		for (int index0 = 0; index0 < pontoRegistros.length; index0++) {
			if (pontoRegistros[index0].getHoraPonto().compareTo(atualizarPontoRegistros[index0].getHoraPontoPosterior()) != 0) {
				for (int index1 = 0; index1 < pontoRegistros.length; index1++) {
					
					if (index0 > 0 && index1 < index0) {
						if (atualizarPontoRegistros[index0].getHoraPontoPosterior()
								.compareTo(atualizarPontoRegistros[index1].getHoraPontoPosterior()) < 0) {
							new ValidarPontoRegistroException(
									"Esta hora está sendo menor que o ponto de registro anterior");
						}
					}
					
					if(index0 >= 0 && index1 >= index0) {
						if (atualizarPontoRegistros[index0].getHoraPontoPosterior()
								.compareTo(atualizarPontoRegistros[index1].getHoraPontoPosterior()) > 0) {
							new ValidarPontoRegistroException(
									"Esta hora está sendo maior que o ponto de registro posterior");
						}
					}
				}
			}
		}
	}
	
	public static void validarPontoAtualizacaoPendente(List<Object> listaPendencia) {
		
		if(!listaPendencia.isEmpty()) {
			throw new ValidarPontoRegistroException("Ainda existe uma verificação pendente para este dia");
		}
	}

}
