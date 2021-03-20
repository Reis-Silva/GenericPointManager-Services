package com.generic.pointmanager.models.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generic.pointmanager.models.entity.PontoRegistro;

public interface PontoRegistroRepository extends JpaRepository<PontoRegistro, Long>{
	 
	 @Query(value = "SELECT * FROM pontoregistro.pontoregistro where id_usuario = ?", nativeQuery = true)
	 List<PontoRegistro> buscarPorUsuarioId(Long id);
	 
	 @Query(value = "SELECT * FROM pontoregistro.pontoregistro where id_usuario = ? and Date(data_ponto) between ? and ?", nativeQuery = true)
	 List<PontoRegistro> buscarPorUsuarioIdEspecifico(Long id, Date datainicial, Date datafinal);
	 
	 @Query(value = "SELECT * FROM pontoregistro.pontoregistro where id_usuario = ? and id = ?", nativeQuery = true)
	 PontoRegistro buscarPorUsuarioIdAndPontoRegistroId(Long idUsuario, Long idPontoRegistro);
	 
	 @Query(value = "SELECT * FROM pontoregistro.pontoregistro where data_ponto = ?", nativeQuery = true)
	 List<PontoRegistro> buscarPorDataHoraPonto(Date dataHoraPonto);
}
