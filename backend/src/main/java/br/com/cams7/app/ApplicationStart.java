package br.com.cams7.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import br.com.cams7.app.audit.AuditorAwareImpl;
import br.com.cams7.app.model.UserEntity;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ApplicationStart {
	
	@Bean
	public Module module() {
		return new Hibernate5Module();
	}

	@Bean
	public AuditorAware<UserEntity> auditorAware() {
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}

}
