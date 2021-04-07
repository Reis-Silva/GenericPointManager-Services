package com.generic.pointmanager.models.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import com.generic.pointmanager.models.entity.PontoRegistro;

public interface PontoRegistroRepository extends JpaRepository<PontoRegistro, Long>{
	 
	 @Query(value = "SELECT * FROM administrativo.ponto_registro where data_ponto = cast(? AS Date) and id_usuario = ?", nativeQuery = true)
	 List<PontoRegistro> buscarPorDataHoraPonto(Date dataPonto, Long idUsuario);
	 
	 @Query(value = "SELECT * FROM administrativo.ponto_registro where id_usuario = ?", nativeQuery = true)
	 List<PontoRegistro> buscarPorUsuarioId(BigInteger id);
	 
	 @Query(value = "SELECT * FROM administrativo.ponto_registro where id_usuario = ? and data_ponto between ? and ? ", nativeQuery = true)
	 List<PontoRegistro> buscarPorUsuarioIdEspecifico(Long idUsuario, Date datainicial, Date datafinal);
	 
	 @Query(value = "SELECT * FROM administrativo.ponto_registro where id_usuario = ? and id_ponto_registro = ?", nativeQuery = true)
	 PontoRegistro buscarPorUsuarioIdAndPontoRegistroId(Long idUsuario, Long idPontoRegistro);
	 
}
