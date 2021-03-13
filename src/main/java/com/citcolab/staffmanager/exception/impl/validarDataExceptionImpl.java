package com.citcolab.staffmanager.exception.impl;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.citcolab.staffmanager.exception.validarDataException;

@Service
public class validarDataExceptionImpl{
	
	public void validarDataHora(Calendar data) {
		
		data.get(Calendar.DAY_OF_WEEK);
		
		if(data.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || data.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			throw new validarDataException("Dias não uteis");
		}
	}
}
