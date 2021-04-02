package com.generic.pointmanager.models.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String Justificativa;
	
	@Column(name = "numero_registro")
	private BigInteger numeroRegistro;
	
}
