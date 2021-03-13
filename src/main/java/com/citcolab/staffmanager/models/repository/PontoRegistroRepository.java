package com.citcolab.staffmanager.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citcolab.staffmanager.models.entity.PontoRegistro;

public interface PontoRegistroRepository extends JpaRepository<PontoRegistro, Long>{
	
	 @Query("SELECT id_usuario FROM pontoregistro.pontoregistro WHERE id = :id")
	 Optional<PontoRegistro> findById(Long id);
}
