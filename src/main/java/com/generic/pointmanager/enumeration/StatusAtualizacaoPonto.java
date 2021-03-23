package com.generic.pointmanager.enumeration;

import com.generic.pointmanager.enumeration.api.IEnumModel;

public enum StatusAtualizacaoPonto implements IEnumModel{
	
	APROVADO(0, "APROVADO"),
	RECUSADO(1, "RECUSADO");
	
	private String descricao;
	
	private Integer valor;
	
	StatusAtualizacaoPonto(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

	@Override
	public Integer getValor() {
		return this.valor;
	}

}
