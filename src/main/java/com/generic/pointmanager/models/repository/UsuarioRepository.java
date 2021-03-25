package com.generic.pointmanager.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generic.pointmanager.models.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	boolean existsByEmail(String Email);
	
	boolean existsByCpf(String cpf);
	
	boolean existsByEmployerId(Long EmployerId);
	
	Usuario findByCpf(String cpf);
	
}
