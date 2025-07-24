package com.alex.proposta_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PropostaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaAppApplication.class, args);
	}

}
