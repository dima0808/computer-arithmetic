package com.civka.calculatordemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@SpringBootApplication
public class CalculatorDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorDemoApplication.class, args);
	}

	@Bean
	SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
}
