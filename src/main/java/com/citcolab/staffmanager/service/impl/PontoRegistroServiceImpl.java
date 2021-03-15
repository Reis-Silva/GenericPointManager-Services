package com.citcolab.staffmanager.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citcolab.staffmanager.exception.impl.validarDataExceptionImpl;
import com.citcolab.staffmanager.models.entity.PontoRegistro;
import com.citcolab.staffmanager.models.entity.Usuario;
import com.citcolab.staffmanager.models.repository.PontoRegistroRepository;
import com.citcolab.staffmanager.models.repository.UsuarioRepository;
import com.citcolab.staffmanager.service.PontoRegistroService;
import com.citcolab.staffmanager.ws.GerenciadorRepositoryService;

@Service
@RequestMapping("/pontoregistro")
public class PontoRegistroServiceImpl implements PontoRegistroService{
	
	@Autowired
	private validarDataExceptionImpl validarDataExceptionImpl;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PontoRegistroRepository pontoRegistroRepository;
	
	@Autowired
	private GerenciadorRepositoryService gerenciadorRepositoryService;
	
	@GetMapping("/registrar/{id}")
	public ResponseEntity registrarPonto(@PathVariable Long id){
		
		if(!usuarioRepository.existsById(id)) {
			throw new RuntimeException("Usuario não existente");
		}
		
		PontoRegistro pontoRegistro = new PontoRegistro();
		
		Calendar data = Calendar.getInstance();
		
		validarDataExceptionImpl.validarDataHora(data);
		
		pontoRegistro.setDataPonto(data.getTime());
		pontoRegistro.setHoraPonto(data.getTime());
		pontoRegistro.setLocalPonto("fajuta");
		pontoRegistro.setUsuario(new Usuario());
		pontoRegistro.getUsuario().setId(id);
		
		gerenciadorRepositoryService.persistirRegistroRepository(pontoRegistro);
		
		return ResponseEntity.ok(pontoRegistro);
	}
	
	@GetMapping("/procurar/{id}")
	public ResponseEntity<List<PontoRegistro>> procurarPonto(@PathVariable("id") Long id) {
		
		List<PontoRegistro> pontoRegistros = new ArrayList<PontoRegistro>();
		
		pontoRegistros.addAll(pontoRegistroRepository.buscarPorUsuarioId(id));
		
		return ResponseEntity.ok(pontoRegistros);
	}
	
	@PostMapping("/atualizar/{idUsuario}")
	public ResponseEntity atualizarPonto(@PathVariable("idUsuario") Long idUsuario, @RequestBody PontoRegistro pontoRegistro) {
		
		PontoRegistro pontoRegistroAtualizar = new PontoRegistro();
		
		pontoRegistroAtualizar = 
				pontoRegistroRepository.buscarPorUsuarioIdAndPontoRegistroId(idUsuario, pontoRegistro.getId());
		
		if(pontoRegistroAtualizar == null ){
			validarDataExceptionImpl.validarPontoDataHora();
		}
		
		pontoRegistroAtualizar.setDataPonto(pontoRegistro.getDataPonto());
		pontoRegistroAtualizar.setHoraPonto(pontoRegistro.getHoraPonto());
		
		gerenciadorRepositoryService.persistirRegistroRepository(pontoRegistroAtualizar);

		return ResponseEntity.ok(pontoRegistroAtualizar);
	}
	
}