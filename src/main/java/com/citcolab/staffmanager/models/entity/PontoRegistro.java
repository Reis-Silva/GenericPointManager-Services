package com.citcolab.staffmanager.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "pontoregistro" , schema = "pontoregistro")
public class PontoRegistro {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_Ponto", nullable = false)
	private String dataPonto; 
	
	@Column(name = "local_Ponto", nullable = false)
	private String localPonto;
	
	@Column(name = "hora_Ponto", nullable = false)
	private String horaPonto;
	
	@OneToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
}
