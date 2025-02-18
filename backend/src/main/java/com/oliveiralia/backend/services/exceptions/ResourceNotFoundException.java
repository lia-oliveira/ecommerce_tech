package com.oliveiralia.backend.services.exceptions;

import com.oliveiralia.backend.dto.ProductDTO;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String msg) {
		super(msg);
		
	}
	
}
