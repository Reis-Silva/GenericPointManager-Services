package com.citcolab.staffmanager.service;

import java.util.Optional;

import com.citcolab.staffmanager.models.entity.PontoRegistro;

public interface PontoRegistroService {

	Optional<PontoRegistro> obterUsuarioGetId(Long id);
}
