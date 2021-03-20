package com.generic.pointmanager.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
@Table( name = "usuario" , schema = "pontoregistro")
public class Usuario {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "employer_id", nullable = false)
	private Long employerId;
	
	@CPF(message = "CPF inválido")
	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;
	
	@Column(name = "nome", nullable = false)
    private String nome;
    
	@Column(name = "email", nullable = false)
    private String email;
    
	@Column(name = "senha", nullable = false)
    private String senha;
    
	@Column(name = "office", nullable = false)
    private String office;
    
	@Column(name = "local_office", nullable = false)
    private String localOffice;
    
	@Column(name = "photo_profile_uri")
    private String photoProfileUri;
    
}
