package com.citcolab.staffmanager.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm:ss");
		
		pontoRegistro.setDataPonto(formatarData.format(data.getTime()));
		
		pontoRegistro.setHoraPonto(formatarHora.format(data.getTime()));
		
		pontoRegistro.setLocalPonto("fajuta");
		
		pontoRegistro.setUsuario(new Usuario());
		
		pontoRegistro.getUsuario().setId(id);
		
		gerenciadorRepositoryService.persistirRegistroRepository(pontoRegistro);
		
		return ResponseEntity.ok(pontoRegistro);
	}
	
	@GetMapping("/procurar/{id}")
	public ResponseEntity<Optional<PontoRegistro>> ProcurarPonto(@PathVariable Long id) {
		
		List<PontoRegistro> pontoRegistro = new ArrayList<>();
		
		Optional<PontoRegistro> pontoRegistro2 = Optional.empty();		
		
		pontoRegistro2 = pontoRegistroRepository.findById(id);
		
		return ResponseEntity.ok(pontoRegistro2);
	}

	@Override
	public Optional<PontoRegistro> obterUsuarioGetId(Long id) {
		return pontoRegistroRepository.findById(id);
	}
	
	
}