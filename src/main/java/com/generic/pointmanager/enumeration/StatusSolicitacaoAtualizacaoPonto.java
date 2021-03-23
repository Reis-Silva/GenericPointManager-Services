package com.generic.pointmanager.enumeration;

import com.generic.pointmanager.enumeration.api.IEnumModel;

public enum StatusSolicitacaoAtualizacaoPonto implements IEnumModel{
	
	PENDENTE(0,"PENDENTE"),
	VERIFICADA(1,"VERIFICADA");
	
	private String descricao;
	
	private Integer valor;
	
	StatusSolicitacaoAtualizacaoPonto(int valor, String descricao) {
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
