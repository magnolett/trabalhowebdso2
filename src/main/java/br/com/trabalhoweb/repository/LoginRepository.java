package br.com.trabalhoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.trabalhoweb.entities.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long>{

	@Query
	public User findByEmailAndPassword(String email, String password);

	@Query
	public User findByEmail(String email);
}
