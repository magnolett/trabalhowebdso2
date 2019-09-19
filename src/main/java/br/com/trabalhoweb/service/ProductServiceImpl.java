package br.com.trabalhoweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.trabalhoweb.entities.Product;
import br.com.trabalhoweb.repository.ProductRepository;

@Component
public class ProductServiceImpl {

	@Autowired
	private ProductRepository repository;
	
	public Product save(Product product) {
		return repository.save(product);
	}
	
	public Product findById(Long id) {
		return repository.findById(id).get();
	}
	
	public void delete (Product product) {
		repository.delete(product);
	}
}
