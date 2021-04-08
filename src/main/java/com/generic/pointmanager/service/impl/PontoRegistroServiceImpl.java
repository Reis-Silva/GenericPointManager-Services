package com.generic.pointmanager.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.generic.pointmanager.exception.ValidarDataException;
import com.generic.pointmanager.json.JsonPontoRegistroSerializer;
import com.generic.pointmanager.models.entity.PontoRegistro;
import com.generic.pointmanager.models.entity.Usuario;
import com.generic.pointmanager.models.repository.PontoRegistroRepository;
import com.generic.pointmanager.models.repository.UsuarioRepository;
import com.generic.pointmanager.service.PontoRegistroService;
import com.generic.pointmanager.ws.GerenciadorRepositoryService;

@Service
@RestController
@RequestMapping("/pontoregistro")
public class PontoRegistroServiceImpl implements PontoRegistroService{
		
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PontoRegistroRepository pontoRegistroRepository;
	
	@Autowired
	private GerenciadorRepositoryService gerenciadorRepositoryService;
	
	@GetMapping("/registrar/{idUsuario}")
	@Override
	public ResponseEntity registrarPonto(@PathVariable("idUsuario") Long idUsuario) {
		
		if(!usuarioRepository.existsById(idUsuario)) {
			throw new RuntimeException("Usuario não existe");
		}
		
		Calendar data = Calendar.getInstance();
		
		ValidarDataException.validarDataHora(data);
		
		List<PontoRegistro> pontoRegistros = new ArrayList<PontoRegistro>();
		
		pontoRegistros.addAll(pontoRegistroRepository.buscarPorDataHoraPonto(data.getTime(), idUsuario));
		
		if(pontoRegistros.size() > 0) {
			
			Date horaPontoRegistro = pontoRegistros.get(pontoRegistros.size()-1).getHoraPonto();
			
			ValidarDataException.validarPontoDataHora(new PontoRegistro(), pontoRegistros, pontoRegistros.size());
			
			ValidarDataException.validarCronometro(data, horaPontoRegistro);
		}
		
		PontoRegistro pontoRegistro = new PontoRegistro();
		
		pontoRegistro.setDataPonto(data.getTime());
		pontoRegistro.setHoraPonto(data.getTime());
		pontoRegistro.setLocalPonto("fajuta");
		pontoRegistro.setNumeroPonto(pontoRegistros.size() + 1);
		pontoRegistro.setUsuario(new Usuario());
		pontoRegistro.getUsuario().setId(idUsuario);
		
		gerenciadorRepositoryService.persistirPontoRegistro(pontoRegistro);
		
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule();
		module.addSerializer(PontoRegistro.class, new JsonPontoRegistroSerializer());
		mapper.registerModule(module);
		String serialized = null;
		try {
			serialized = mapper.writeValueAsString(pontoRegistro);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(serialized);
	}
	
	
	@GetMapping("/procurarpontosdiarios/{idUsuario}")
	@Override
	public ResponseEntity<List<PontoRegistro>> procurarPontoDia(@PathVariable("idUsuario") Long idUsuario) {
		
		if(!usuarioRepository.existsById(idUsuario)) {
			throw new RuntimeException("Usuario não existe");
		}
		
		List<PontoRegistro> pontoRegistros = new ArrayList<PontoRegistro>();
		
		Calendar data = Calendar.getInstance();
		
		pontoRegistros.addAll(pontoRegistroRepository.buscarPorDataHoraPonto(data.getTime(), idUsuario));
		
		return ResponseEntity.ok(pontoRegistros);
		
	}
	
	@GetMapping("/procurar/{idUsuario}")
	@Override
	public ResponseEntity<List<PontoRegistro>> procurarPonto(@PathVariable("idUsuario") BigInteger idUsuario) {
		
		List<PontoRegistro> pontoRegistros = new ArrayList<PontoRegistro>();
		
		pontoRegistros.addAll(pontoRegistroRepository.buscarPorUsuarioId(idUsuario));
		
		return ResponseEntity.ok(pontoRegistros);
	}
	
	@GetMapping("/procurarespecifico/{idUsuario}")
	@Override
	public ResponseEntity<List<PontoRegistro>> procurarPontoEspecifico(
			@PathVariable("idUsuario") Long idUsuario, @QueryParam("dataInicial") @DateTimeFormat(pattern="yyyy-MM-dd") Date dataInicial, @QueryParam("dataFinal") @DateTimeFormat(pattern="yyyy-MM-dd") Date dataFinal) {
		
		ValidarDataException.filtroData(dataInicial, dataFinal);
		
		List<PontoRegistro> pontoRegistros = new ArrayList<PontoRegistro>();
		
		pontoRegistros.addAll(pontoRegistroRepository.buscarPorUsuarioIdEspecifico(idUsuario, dataInicial, dataFinal));
		
		return ResponseEntity.ok(pontoRegistros);
	}
		
}
