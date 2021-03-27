package com.generic.pointmanager.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generic.pointmanager.enumeration.StatusSolicitacaoAtualizacaoPonto;
import com.generic.pointmanager.exception.ValidarDataException;
import com.generic.pointmanager.exception.ValidarNivelAcessoException;
import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;
import com.generic.pointmanager.models.entity.PontoRegistro;
import com.generic.pointmanager.models.repository.PontoRegistroRepository;
import com.generic.pointmanager.models.repository.UsuarioRepository;
import com.generic.pointmanager.service.AtualizarPontoRegistroService;
import com.generic.pointmanager.ws.GerenciadorRepositoryService;

@RequestMapping("/atualizadarPontoRegistro")
public class AtualizarPontoRegistroServiceImpl implements AtualizarPontoRegistroService{
	
	@Autowired
	private PontoRegistroRepository pontoRegistroRepository;
	
	@Autowired
	private GerenciadorRepositoryService gerenciadorRepositoryService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/solicitar/{idUsuario}/{idPontoRegistro}")
	@Override
	public ResponseEntity solicitarAtualizarPonto(
			@PathVariable("idUsuario") Long idUsuario, @PathVariable("idPontoRegistro") Long idPontoRegistro, 
			@RequestBody AtualizarPontoRegistro atualizarPontoRegistro) {
		
		PontoRegistro pontoRegistroAtualizar = new PontoRegistro();
		
		pontoRegistroAtualizar = 
				pontoRegistroRepository.buscarPorUsuarioIdAndPontoRegistroId(idUsuario, idPontoRegistro);
		
		ValidarDataException.validarPontoDataHora(pontoRegistroAtualizar, null, null);
		
		ValidarNivelAcessoException.validarNivelAcesso(
	    		usuarioRepository.findByCpf(pontoRegistroAtualizar.getUsuario().getCpf()).getNivelAcesso().getDescricao());
		
		AtualizarPontoRegistro solicitarAtualizacao = new AtualizarPontoRegistro();
		
		Calendar dataAtual = Calendar.getInstance();
		
		solicitarAtualizacao.setHoraPontoPosterior(pontoRegistroAtualizar.getHoraPonto());
		solicitarAtualizacao.setDataSolicitacao(dataAtual.getTime());
		solicitarAtualizacao.getIdPontoRegistro().setId(pontoRegistroAtualizar.getId());
		solicitarAtualizacao.setStatusSolicitacaoAtualizacaoPonto(StatusSolicitacaoAtualizacaoPonto.PENDENTE);
		solicitarAtualizacao.setJustificativaAtualizacaoPonto(atualizarPontoRegistro.getJustificativaAtualizacaoPonto());
		
		gerenciadorRepositoryService.persistirAtualizarRegistro(solicitarAtualizacao);

		return ResponseEntity.ok(solicitarAtualizacao);
	}

	@Override
	public ResponseEntity listaAtualizarPonto(Long idUsuario, Long idPontoRegistro,
			AtualizarPontoRegistro atualizarPontoRegistro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity listaAtualizarPontoEspecifico(Long idUsuario, Long idPontoRegistro,
			AtualizarPontoRegistro atualizarPontoRegistro) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResponseEntity atualizarPonto(Long idUsuario, Long idPontoRegistro,
			AtualizarPontoRegistro atualizarPontoRegistro) {
		
		return null;
	}
	
	
}
