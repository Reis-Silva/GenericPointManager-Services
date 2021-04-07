package com.generic.pointmanager.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;

public interface AtualizarPontoRegistroRepository extends JpaRepository<AtualizarPontoRegistro, Long>{
	
	@Query(value = "select * from administrativo.atualizar_ponto_registro where status_atualizacao_ponto='PENDENTE'", nativeQuery = true)
	List<AtualizarPontoRegistro> buscarAtualizarListaGeralPontoRegistro();
	
	@Query(value = "select \r\n"
			+ "	aapr.id_atualizar_ponto_registro, aapr.hora_ponto_atualizacao, aapr.solicitacao_atualizacao_ponto, aapr.status_atualizacao_ponto,\r\n"
			+ "	apr.id_ponto_registro as apr_idpontoregistro, \r\n"
			+ "	au.id_usuario as au_idusuario,\r\n"
			+ "	ar.id_registro, ar.justificativa, ar.data_solicitacao, ar.numero_registro\r\n"
			+ "from administrativo.atualizar_ponto_registro aapr\r\n"
			+ "	inner join administrativo.ponto_registro apr on aapr.id_ponto_registro=apr.id_ponto_registro\r\n"
			+ "	inner join administrativo.usuario au on apr.id_usuario= au.id_usuario\r\n"
			+ "	inner join administrativo.registro ar on aapr.numero_registro=ar.numero_registro\r\n"
			+ "where status_atualizacao_ponto='PENDENTE' \r\n"
			+ "	and au.id_usuario = ?	\r\n"
			+ "order by ar.data_solicitacao desc", nativeQuery = true)
	List<Object> buscarAtualizarListaUsuarioPontoRegistroEspecifico(Long idUsuario);
	
	@Query(value = "select \r\n"
			+ "	aapr.id_atualizar_ponto_registro,\r\n"
			+ "	apr.id_ponto_registro as apr_idpontoregistro, \r\n"
			+ "	au.id_usuario as au_idusuario\r\n"
			+ "from administrativo.atualizar_ponto_registro aapr\r\n"
			+ "	inner join administrativo.ponto_registro apr on aapr.id_ponto_registro=apr.id_ponto_registro\r\n"
			+ "	inner join administrativo.usuario au on apr.id_usuario= au.id_usuario\r\n"
			+ "where status_atualizacao_ponto='PENDENTE' \r\n"
			+ "	and apr.id_ponto_registro = ?", nativeQuery = true)
	List<Object> buscarAtualizarListaUsuarioPontoRegistroPendente(Long IdPontoRegistro);
}
