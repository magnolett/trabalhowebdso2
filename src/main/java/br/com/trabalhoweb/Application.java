package br.com.trabalhoweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.trabalhoweb.repository.ProductRepository;
import br.com.trabalhoweb.ui.LoginUI;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@EnableJpaRepositories
@ComponentScan
public class Application implements CommandLineRunner {

	@Autowired
	private LoginUI loginUI;
	
	@Autowired
	private ProductRepository repository;
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);

	    builder.headless(false);
	    ConfigurableApplicationContext context = builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		loginUI.criaTela();
		
	}
}
