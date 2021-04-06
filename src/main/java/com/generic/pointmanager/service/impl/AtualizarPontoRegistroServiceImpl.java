package com.generic.pointmanager.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generic.pointmanager.enumeration.StatusAtualizacaoPonto;
import com.generic.pointmanager.enumeration.StatusSolicitacaoAtualizacaoPonto;
import com.generic.pointmanager.exception.ValidarCpfException;
import com.generic.pointmanager.exception.ValidarDataException;
import com.generic.pointmanager.exception.ValidarNivelAcessoException;
import com.generic.pointmanager.exception.ValidarPontoRegistroException;
import com.generic.pointmanager.models.entity.AtualizarPontoRegistro;
import com.generic.pointmanager.models.entity.CollectionRegistro;
import com.generic.pointmanager.models.entity.PontoRegistro;
import com.generic.pointmanager.models.entity.Registro;
import com.generic.pointmanager.models.entity.Usuario;
import com.generic.pointmanager.models.repository.AtualizarPontoRegistroRepository;
import com.generic.pointmanager.models.repository.PontoRegistroRepository;
import com.generic.pointmanager.models.repository.RegistroRepository;
import com.generic.pointmanager.models.repository.UsuarioRepository;
import com.generic.pointmanager.service.AtualizarPontoRegistroService;
import com.generic.pointmanager.ws.GerenciadorRepositoryService;
import com.generic.pointmanager.ws.impl.GetCollection;

@Service
@RestController
@RequestMapping("/atualizarPontoRegistro")
public class AtualizarPontoRegistroServiceImpl implements AtualizarPontoRegistroService{
	
	@Autowired
	private PontoRegistroRepository pontoRegistroRepository;
	
	@Autowired
	private GerenciadorRepositoryService gerenciadorRepositoryService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private AtualizarPontoRegistroRepository atualizarPontoRegistroRepository;
	
	@Autowired
	private RegistroRepository registroRepository;
	
	private PontoRegistroServiceImpl pontoRegistroServiceImpl;
	
	@PostMapping("/solicitar")
	@Override
	public ResponseEntity solicitarAtualizarPonto(@RequestBody GetCollection collection) {
			
		ValidarPontoRegistroException.validarPontoRegistroNumero(collection.getCollection().getPontoRegistro(), collection.getCollection().getAtualizarPontoRegistro());
		
		BigInteger numeroRegistro = registroRepository.maiorNumeroRegistro();
		
		if(numeroRegistro == null) {
			numeroRegistro = BigInteger.valueOf(1);
		}
		
		Calendar dataAtual = Calendar.getInstance();
		
		collection.getCollection().getRegistro().setNumeroRegistro(numeroRegistro);
		collection.getCollection().getRegistro().setDataSolicitacao(dataAtual.getTime());
		
		gerenciadorRepositoryService.persitirRegistro(collection.getCollection().getRegistro());
		
		AtualizarPontoRegistro solicitarAtualizacao = new AtualizarPontoRegistro();
		
		for(int index=0; index < collection.getCollection().getPontoRegistro().length; index++) {
			
			if(collection.getCollection().getPontoRegistro()[index].getHoraPonto().compareTo(
					collection.getCollection().getAtualizarPontoRegistro()[index].getHoraPontoPosterior()) !=0) {
				solicitarAtualizacao.setHoraPontoPosterior(collection.getCollection().getAtualizarPontoRegistro()[index].getHoraPontoPosterior());
				solicitarAtualizacao.setIdPontoRegistro(new PontoRegistro());
				solicitarAtualizacao.getIdPontoRegistro().setId(collection.getCollection().getPontoRegistro()[index].getId());
				solicitarAtualizacao.setStatusSolicitacaoAtualizacaoPonto(StatusSolicitacaoAtualizacaoPonto.PENDENTE);
				solicitarAtualizacao.setStatusAtualizacaoPonto(StatusAtualizacaoPonto.PENDENTE);
				solicitarAtualizacao.setNumeroRegistro(numeroRegistro);
				
				gerenciadorRepositoryService.persistirAtualizarPontoRegistro(solicitarAtualizacao);
			}
		}
		
		return ResponseEntity.noContent().build();
	}	
	
	@GetMapping("/procurargeral/{idAdmin}")
	@Override
	public ResponseEntity listaAtualizarPonto(@PathVariable("idAdmin") Long idAdmin) {
		
		ValidarNivelAcessoException.validarNivelAcesso(
				usuarioRepository.findById(idAdmin).get().getNivelAcesso().getDescricao(), null);
		
		return ResponseEntity.ok(
				atualizarPontoRegistroRepository.buscarAtualizarListaGeralPontoRegistro());
	}
	
	@GetMapping("/procurarespecificousuario/{idAdmin}/{cpf}")
	@Override
	public ResponseEntity listaAtualizarPontoEspecifico(@PathVariable("idAdmin") Long idAdmin, @PathVariable("cpf") String cpf) {
		
		ValidarCpfException.validarCPF(usuarioRepository.existsByCpf(cpf), "find");
		
		ValidarNivelAcessoException.validarNivelAcesso(
				usuarioRepository.findById(idAdmin).get().getNivelAcesso().getDescricao(), null);
		
		return ResponseEntity.ok(
				atualizarPontoRegistroRepository.buscarAtualizarListaUsuarioPontoRegistroEspecifico(usuarioRepository.findByCpf(cpf).getId()));
	}
	
	@GetMapping("/listaVisaoPontosDiariosConjuntoAtualizar/{idAdmin}/{idAtualizarPontoRegistro}")
	@Override
	public ResponseEntity listaVisaoPontosDiariosConjuntoAtualizar(@PathVariable("idAdmin") Long idAdmin,
			@PathVariable("idAtualizarPontoRegistro") Long idAtualizarPontoRegistro) {
		
		Optional<AtualizarPontoRegistro> atualizacao = atualizarPontoRegistroRepository.findById(idAtualizarPontoRegistro);
		
		Optional<PontoRegistro> pontoRegistroAtualizar = pontoRegistroRepository.findById(atualizacao.get().getId());
		
		List<PontoRegistro> pontosdiariosAtualizacao = new ArrayList<PontoRegistro>();
		
		
		pontosdiariosAtualizacao.addAll((Collection<? extends PontoRegistro>) pontoRegistroServiceImpl.procurarPontoEspecifico(
				pontoRegistroAtualizar.get().getId(), pontoRegistroAtualizar.get().getDataPonto(), pontoRegistroAtualizar.get().getDataPonto()));
		
		return ResponseEntity.ok(pontosdiariosAtualizacao);
	}
	
	@Override
	public ResponseEntity atualizarPonto(@PathVariable("idAdmin") Long idAdmin, @PathVariable("idAtualizarPontoRegistro") Long idAtualizarPontoRegistro,
			AtualizarPontoRegistro atualizarPontoRegistro) {
		
		Optional<AtualizarPontoRegistro> atualizacao = atualizarPontoRegistroRepository.findById(idAtualizarPontoRegistro);
		
		Optional<PontoRegistro> pontoRegistroAtualizar = pontoRegistroRepository.findById(atualizacao.get().getId());
		
		Calendar dataAtual = Calendar.getInstance();
		
		pontoRegistroAtualizar.get().setHoraPonto(atualizacao.get().getHoraPontoPosterior());
		
		
		Atualizacao.setHoraPontoPosterior(pontoRegistroAtualizar.getHoraPonto());
		Atualizacao.setDataSolicitacao(dataAtual.getTime());
		Atualizacao.getIdPontoRegistro().setId(pontoRegistroAtualizar.getId());
		Atualizacao.setStatusSolicitacaoAtualizacaoPonto(StatusSolicitacaoAtualizacaoPonto.VERIFICADA);
		Atualizacao.setJustificativaAtualizacaoPonto(atualizarPontoRegistro.getJustificativaAtualizacaoPonto());
		
		gerenciadorRepositoryService.persistirAtualizarRegistro(solicitarAtualizacao);

		return ResponseEntity.ok(solicitarAtualizacao);
		
	}
		
}
