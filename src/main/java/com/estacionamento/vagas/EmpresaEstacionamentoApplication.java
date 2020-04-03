package com.estacionamento.vagas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpresaEstacionamentoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmpresaEstacionamentoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	}

}
