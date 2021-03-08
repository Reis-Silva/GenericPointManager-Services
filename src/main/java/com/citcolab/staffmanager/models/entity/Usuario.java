package com.citcolab.staffmanager.models.entity;

import lombok.Data;

@Data
public class Usuario {

	private String employerID;
	
    private String name;
    
    private String email;
    
    private String password;
    
    private String office;
    
    private String localOffice;
    
    private String photoProfileURI;
    
    
}
