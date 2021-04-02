package com.generic.pointmanager.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;

public interface AtualizarPontoRegistroRepository extends JpaRepository<AtualizarPontoRegistro, Long>{
	
	@Query(value = "select * from administrativo.atualizar_ponto_registro where status_atualizacao_ponto='PENDENTE'", nativeQuery = true)
	List<AtualizarPontoRegistro> buscarAtualizarListaGeralPontoRegistro();
	
	@Query(value = "select \r\n"
			+ "	aapr.id_atualizar_ponto_registro, aapr.justificativa_atualizacao_ponto, aapr.hora_ponto_atualizacao, aapr.data_solicitacao, aapr.data_resposta, aapr.solicitacao_atualizacao_ponto,\r\n"
			+ "	apr.id_ponto_registro as apr_idpontoregistro, \r\n"
			+ "	au.id_usuario as au_idusuario\r\n"
			+ "from administrativo.atualizar_ponto_registro aapr\r\n"
			+ "	inner join administrativo.ponto_registro apr on aapr.id_ponto_registro=apr.id_ponto_registro\r\n"
			+ "	inner join administrativo.usuario au on apr.id_usuario= au.id_usuario\r\n"
			+ "where status_atualizacao_ponto='PENDENTE'and au.id_usuario = ?	\r\n"
			+ "order by data_solicitacao desc", nativeQuery = true)
	List<Object> buscarAtualizarListaUsuarioPontoRegistroEspecifico(Long id);
}
