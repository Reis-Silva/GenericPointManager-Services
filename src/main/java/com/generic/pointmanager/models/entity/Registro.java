package com.generic.pointmanager.models.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "registro", schema = "administrativo")
public class Registro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_registro")
	private Long id;
	
	@Column(name = "justificativa", nullable = false)
	private String justificativa;
	
	@Column(name = "numero_registro")
	private BigInteger numeroRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_solicitacao", nullable = false)
	private Date dataSolicitacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_resposta")
	private Date dataResposta;
	
}
