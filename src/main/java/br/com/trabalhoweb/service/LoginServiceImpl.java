package br.com.trabalhoweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.trabalhoweb.entities.User;
import br.com.trabalhoweb.repository.LoginRepository;

@Component
public class LoginServiceImpl {

	@Autowired
	private LoginRepository repository;
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public User findById(Long id) {
		return repository.findById(id).get();
	}
	
	public void delete(User user) {
		repository.delete(user);
	}
	
	public User findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
	
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
}
