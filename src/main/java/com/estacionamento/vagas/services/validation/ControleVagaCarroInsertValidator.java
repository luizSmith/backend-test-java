package com.estacionamento.vagas.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.estacionamento.vagas.domain.ControleVeiculoVagaCarro;
import com.estacionamento.vagas.domain.VagaCarro;
import com.estacionamento.vagas.domain.Veiculo;
import com.estacionamento.vagas.domain.enums.TipoVeiculo;
import com.estacionamento.vagas.dto.ControleVeiculoVagaCarroNewDTO;
import com.estacionamento.vagas.repositories.ControleVeiculoVagaCarroRepository;
import com.estacionamento.vagas.repositories.VagaCarroRepository;
import com.estacionamento.vagas.repositories.VeiculoRepository;
import com.estacionamento.vagas.resource.exception.FieldMessage;

public class ControleVagaCarroInsertValidator implements ConstraintValidator<ControleVagaCarroInsert, ControleVeiculoVagaCarroNewDTO> { //Nome Anotação, TipodaClasse
	
	@Autowired
	private ControleVeiculoVagaCarroRepository repo;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private VagaCarroRepository vagaCarroRepository;	
	
	@Override
	public void initialize(ControleVagaCarroInsert ann) { //programação de inicialização caso necessário
	}
	
	//isValid faz o retorno dizendo se os dados do objeto estão de acordo com a validação
	@Override
	public boolean isValid(ControleVeiculoVagaCarroNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		ControleVeiculoVagaCarro auxEstaciona = repo.buscaControleVeiculo(objDTO.getVeiculoId());
		
		Veiculo auxVeiculo = veiculoRepository.getOne(objDTO.getVeiculoId());
		
		VagaCarro auxVagaCarro = vagaCarroRepository.getOne(objDTO.getVagaCarroId());
		
		if (auxEstaciona != null) {
			list.add(new FieldMessage("Controle Vaga","Veiculo já está no estacionamento"));
		}
		
		if (auxVeiculo.getTipoVeiculo().equals(TipoVeiculo.MOTO)) {
			list.add(new FieldMessage("Controle Vaga","Não é possivel colocar uma moto numa vaga de carro"));
		}
		
		if (auxVagaCarro.getControle() != null) {
			list.add(new FieldMessage("Controle Vaga","A vaga Já está sendo ocupada"));
		}
		
		for (FieldMessage e : list) { //percorre a lista de erro e adiciona o erro personalizado na lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); // se a lista não retornar nenhum erro isValid vai retornar true.
	}
}
