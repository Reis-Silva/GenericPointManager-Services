package com.generic.pointmanager.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.generic.pointmanager.exception.ValidarAutenticacaoException;
import com.generic.pointmanager.exception.ValidarCpfException;
import com.generic.pointmanager.exception.ValidarDataException;
import com.generic.pointmanager.exception.ValidarEmployerIdException;
import com.generic.pointmanager.exception.ValidarNivelAcessoException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	
	@ExceptionHandler(ValidarAutenticacaoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handleValidarAutenticacaoException(ValidarAutenticacaoException ex) {
		String mensagemErro = ex.getMessage();
		
		return new ApiErros(mensagemErro);
	}
	
	@ExceptionHandler(ValidarCpfException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handleValidarCpfException(ValidarCpfException ex) {
		String mensagemErro = ex.getMessage();
		
		return new ApiErros(mensagemErro);
	}
	
	@ExceptionHandler(ValidarDataException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handleValidarDataException(ValidarDataException ex) {
		String mensagemErro = ex.getMessage();
		
		return new ApiErros(mensagemErro);
	}
	
	@ExceptionHandler(ValidarEmployerIdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handleValidarEmployerIdException(ValidarEmployerIdException ex) {
		String mensagemErro = ex.getMessage();
		
		return new ApiErros(mensagemErro);
	}
	
	@ExceptionHandler(ValidarNivelAcessoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handleValidarNivelAcessoException(ValidarNivelAcessoException ex) {
		String mensagemErro = ex.getMessage();
		
		return new ApiErros(mensagemErro);
	}
}
