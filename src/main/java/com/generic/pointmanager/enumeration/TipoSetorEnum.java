package com.generic.pointmanager.enumeration;

import com.generic.pointmanager.enumeration.api.IEnumModel;

public enum TipoSetorEnum implements IEnumModel{
	
	ADMINISTRATIVO(0, "ADMINISTRATIVO"),
	COMERCIAL(1,"COMERCIAL"),
	PROJETOS(2,"PROJETOS");
	
	private String descricao;
	
	private Integer valor;
	
	TipoSetorEnum(int valor, String descricao) {
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
