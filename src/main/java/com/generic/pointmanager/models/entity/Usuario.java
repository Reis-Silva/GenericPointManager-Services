package com.generic.pointmanager.models.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import com.generic.pointmanager.enumeration.TipoNivelAcessoEnum;
import com.generic.pointmanager.enumeration.TipoSetorEnum;
import com.generic.pointmanager.enumeration.TipoSexoEnum;

import lombok.Data;

@Data
@Entity
@Table( name = "usuario" , schema = "usuario")
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo", nullable = false)
	private TipoSexoEnum sexo;
    
	@Column(name = "email", nullable = false)
    private String email;
    
	@Column(name = "senha", nullable = false)
    private String senha;
    
	@Column(name = "office", nullable = false)
    private String office;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "setor", nullable = false)
	private TipoSetorEnum setor;
    
	@Column(name = "local_office", nullable = false)
    private String localOffice;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_nivel_acesso", nullable = false)
	private TipoNivelAcessoEnum tipoNivelAcessoEnum;
    
	@Column(name = "photo_profile_uri")
    private Blob photoProfileUri;
    
}
