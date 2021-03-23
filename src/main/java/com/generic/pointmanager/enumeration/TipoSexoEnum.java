package com.generic.pointmanager.enumeration;

import com.generic.pointmanager.enumeration.api.IEnumModel;

public enum TipoSexoEnum implements IEnumModel{
	
	FEMININO(1,"FEMININO"),
	MASCULINO(1,"MASCULINO");
	
	private String descricao;
	
	private Integer valor;
	
	TipoSexoEnum(int valor, String descricao) {
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
