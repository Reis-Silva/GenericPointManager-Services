package com.generic.pointmanager.models.entity;

import lombok.Data;

@Data
public class CollectionRegistro {
	
	private PontoRegistro[] pontoRegistro;
	
	private AtualizarPontoRegistro[] atualizarPontoRegistro;
	
	private Registro registro;
}
