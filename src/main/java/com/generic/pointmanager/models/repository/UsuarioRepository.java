package com.generic.pointmanager.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generic.pointmanager.models.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	boolean existsByEmail(String Email);
	
	boolean existsByEmployerId(Long EmployerId);
	
	Optional<Usuario> findByEmail(String Email);
	
}
