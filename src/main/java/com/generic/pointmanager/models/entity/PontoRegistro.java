package com.generic.pointmanager.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table( name = "pontoregistro" , schema = "pontoregistro")
public class PontoRegistro {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_Ponto", nullable = false)
	private Date dataPonto; 
	
	@Column(name = "local_Ponto", nullable = false)
	private String localPonto;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_Ponto", nullable = false)
	private Date horaPonto;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
}
