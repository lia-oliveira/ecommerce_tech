package com.oliveiralia.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oliveiralia.backend.dto.ProductDTO;
import com.oliveiralia.backend.entities.Product;
import com.oliveiralia.backend.repositories.ProductRepository;
import com.oliveiralia.backend.services.exceptions.ResourceNotFoundException;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	/*@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);
		return dto;		
	}*/
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
			Product product = repository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Recurso não encontrado."));
			return new ProductDTO(product);		
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = repository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {		
		Product entity = new Product();
		copyDtoToEntity(dto, entity);		
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		Product entity = repository.getReferenceById(id);
		copyDtoToEntity(dto, entity);		
		entity = repository.save(entity);		
		return new ProductDTO(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}

	//Método auxiliar para cópia de dto
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());		
	}

}
