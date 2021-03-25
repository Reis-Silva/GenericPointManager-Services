package com.generic.pointmanager.enumeration;

import com.generic.pointmanager.enumeration.api.IEnumModel;

public enum TipoNivelAcessoEnum implements IEnumModel{
	
	BAIXO(0,"BAIXO"),
	MEDIO(1,"MEDIO"),
	ALTO(2,"ALTO");
	
	private String descricao;
	
	private Integer valor;
	
	TipoNivelAcessoEnum(int valor, String descricao) {
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
