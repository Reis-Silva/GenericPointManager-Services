package com.generic.pointmanager.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generic.pointmanager.enumeration.StatusSolicitacaoAtualizacaoPonto;
import com.generic.pointmanager.exception.ValidarCpfException;
import com.generic.pointmanager.exception.ValidarDataException;
import com.generic.pointmanager.exception.ValidarNivelAcessoException;
import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;
import com.generic.pointmanager.models.entity.PontoRegistro;
import com.generic.pointmanager.models.entity.Registro;
import com.generic.pointmanager.models.entity.Usuario;
import com.generic.pointmanager.models.repository.AtualizarPontoRegistroRepository;
import com.generic.pointmanager.models.repository.PontoRegistroRepository;
import com.generic.pointmanager.models.repository.RegistroRepository;
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
	
	@Autowired
	private AtualizarPontoRegistroRepository atualizarPontoRegistroRepository;
	
	private RegistroRepository registroRepository;
	
	@PostMapping("/solicitar/{idUsuario}/{idPontoRegistro}")
	@Transactional
	@Override
	public ResponseEntity solicitarAtualizarPonto(@RequestBody List<AtualizarPontoRegistro> atualizarPontoRegistro, 
			@RequestBody List<PontoRegistro> pontoRegistro, @RequestBody Registro registro) {
		
		BigInteger numeroRegistro = registroRepository.maiorNumeroRegistro();
		
		if(numeroRegistro == null) {
			numeroRegistro = BigInteger.valueOf(1);
		}
		
		registro.setNumeroRegistro(numeroRegistro);
		
		AtualizarPontoRegistro solicitarAtualizacao = new AtualizarPontoRegistro();
		
		Calendar dataAtual = Calendar.getInstance();
		
		for(int index=0; index == pontoRegistro.size()-1; index++) {
			
			if(pontoRegistro.get(index).getHoraPonto() != solicitarAtualizacao.getHoraPontoPosterior()) {
				solicitarAtualizacao.setHoraPontoPosterior(atualizarPontoRegistro.get(index).getHoraPontoPosterior());
				solicitarAtualizacao.setDataSolicitacao(dataAtual.getTime());
				solicitarAtualizacao.getIdPontoRegistro().setId(pontoRegistro.get(index).getId());
				solicitarAtualizacao.setStatusSolicitacaoAtualizacaoPonto(StatusSolicitacaoAtualizacaoPonto.PENDENTE);
				solicitarAtualizacao.setNumeroRegistro(numeroRegistro);
				
				gerenciadorRepositoryService.persistirAtualizarPontoRegistro(solicitarAtualizacao);
			}
		}
		
		gerenciadorRepositoryService.persitirRegistro(registro);

		return ResponseEntity.ok(atualizarPontoRegistro);
	}	
	
	@GetMapping("/procurargeral/{idAdmin}")
	@Override
	public ResponseEntity listaAtualizarPonto(@PathVariable("idAdmin") Long idAdmin) {
		
		ValidarNivelAcessoException.validarNivelAcesso(
				usuarioRepository.findById(idAdmin).get().getNivelAcesso().getDescricao(), null);
		
		return ResponseEntity.ok(
				atualizarPontoRegistroRepository.buscarAtualizarListaGeralPontoRegistro());
	}
	
	@GetMapping("/procurarespecificousuario/{idAdmin}")
	@Override
	public ResponseEntity listaAtualizarPontoEspecifico(String cpf, @PathVariable("idAdmin") Long idAdmin) {
		
		ValidarCpfException.validarCPF(usuarioRepository.existsByCpf(cpf), "find");
		
		ValidarNivelAcessoException.validarNivelAcesso(
				usuarioRepository.findById(idAdmin).get().getNivelAcesso().getDescricao(), null);
		
		return ResponseEntity.ok(
				atualizarPontoRegistroRepository.buscarAtualizarListaUsuarioPontoRegistroEspecifico(usuarioRepository.findByCpf(cpf).getId()));
	}
	
	@Override
	public ResponseEntity atualizarPonto(Long idAtualizarPontoRegistro, @PathVariable("idAdmin") Long idAdmin,
			AtualizarPontoRegistro atualizarPontoRegistro) {
		
		Optional<AtualizarPontoRegistro> Atualizacao = atualizarPontoRegistroRepository.findById(idAtualizarPontoRegistro);
		
		ValidarNivelAcessoException.validarNivelAcesso(
				usuarioRepository.findById(idAdmin).get().getNivelAcesso().getDescricao(), );
		
		ValidarDataException.validarPontoDataHora(pontoRegistroAtualizar, null, null);
		
		Calendar dataAtual = Calendar.getInstance();
		
		Atualizacao.setHoraPontoPosterior(pontoRegistroAtualizar.getHoraPonto());
		Atualizacao.setDataSolicitacao(dataAtual.getTime());
		Atualizacao.getIdPontoRegistro().setId(pontoRegistroAtualizar.getId());
		Atualizacao.setStatusSolicitacaoAtualizacaoPonto(StatusSolicitacaoAtualizacaoPonto.VERIFICADA);
		Atualizacao.setJustificativaAtualizacaoPonto(atualizarPontoRegistro.getJustificativaAtualizacaoPonto());
		
		gerenciadorRepositoryService.persistirAtualizarRegistro(solicitarAtualizacao);

		return ResponseEntity.ok(solicitarAtualizacao);
		
		return null;
	}

		
}
