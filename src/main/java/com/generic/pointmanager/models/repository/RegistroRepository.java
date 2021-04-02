package com.generic.pointmanager.models.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generic.pointmanager.models.entity.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long>{

	@Query(value = "Select Max(numero_registro) from administrativo.registro", nativeQuery = true)
	BigInteger maiorNumeroRegistro();

}
