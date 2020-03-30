package com.estacionamento.vagas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estacionamento.vagas.domain.Empresa;
import com.estacionamento.vagas.domain.Endereco;
import com.estacionamento.vagas.domain.Veiculo;
import com.estacionamento.vagas.repositories.EmpresaRepository;
import com.estacionamento.vagas.repositories.EnderecoRepository;
import com.estacionamento.vagas.repositories.VeiculoRepository;

@SpringBootApplication
public class EmpresaEstacionamentoApplication implements CommandLineRunner {
	
	// Ele  tem que chamar o obj de acesso a dados uma dependencia
		@Autowired //quando uma dependencia e declada com Autowired ela vai ser instanciada pela spring
		private EmpresaRepository empresaRepository;
		
		@Autowired
		private EnderecoRepository enderecoRepository; 
		
		@Autowired
		private VeiculoRepository veiculoRepository; 

	public static void main(String[] args) {
		SpringApplication.run(EmpresaEstacionamentoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	
		Empresa emp = new Empresa(null, "Orange Bird", "60.140.684/0001-59");
		
		emp.getTelefones().addAll(Arrays.asList("207806534","196786755"));
		
		Endereco end = new Endereco(null, "Av. Presidente Kennedy", "202", null, "Canto do forte", "11702-050", emp);
		
		emp.setEndereco(end);
		
		empresaRepository.save(emp);
		enderecoRepository.save(end);
		
		Veiculo v1 = new Veiculo(null, "Chevrolet", "Onix", "Prata", "ONZ 3459");
		Veiculo v2 = new Veiculo(null, "Kawazaki", "Ninja", "Verde", "CPX 4589");
		
		veiculoRepository.saveAll(Arrays.asList(v1,v2));
		
	}

}
