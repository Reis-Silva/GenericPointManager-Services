package com.generic.pointmanager.models.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.generic.pointmanager.enumeration.StatusAtualizacaoPonto;
import com.generic.pointmanager.enumeration.StatusSolicitacaoAtualizacaoPonto;

import lombok.Data;

@Data
@Entity
@Table(name = "atualizar_ponto_registro", schema = "administrativo")
public class AtualizarPontoRegistro{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atualizar_ponto_registro", nullable = false)
	private Long id;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_ponto_atualizacao", nullable = false)
	private Date horaPontoPosterior;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "solicitacao_atualizacao_ponto", nullable = false)
	private StatusSolicitacaoAtualizacaoPonto statusSolicitacaoAtualizacaoPonto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_atualizacao_ponto", nullable = false)
	private StatusAtualizacaoPonto statusAtualizacaoPonto;
	
	@Column(name = "numero_registro", nullable = false)
	private BigInteger numeroRegistro;
	
	@OneToOne
	@JoinColumn(name = "id_ponto_registro", nullable = false)
	private PontoRegistro idPontoRegistro;
	
}
