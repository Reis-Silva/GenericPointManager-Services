package com.citcolab.staffmanager.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citcolab.staffmanager.models.entity.PontoRegistro;

public interface PontoRegistroRepository extends JpaRepository<PontoRegistro, Long>{
	 
	 @Query(value = "SELECT * FROM pontoregistro.pontoregistro where id_usuario = ?", nativeQuery = true)
	 List<PontoRegistro> buscarPorUsuarioId(Long id);
}
