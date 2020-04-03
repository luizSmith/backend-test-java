package com.estacionamento.vagas.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.estacionamento.vagas.domain.ControleVeiculoVagaCarro;
import com.estacionamento.vagas.dto.ControleVeiculoVagaCarroDTO;
import com.estacionamento.vagas.repositories.ControleVeiculoVagaCarroRepository;
import com.estacionamento.vagas.resource.exception.FieldMessage;

public class ControleVeiculoVagaCarroUpdateValidator implements ConstraintValidator<ControleVeiculoVagaCarroUpdate, ControleVeiculoVagaCarroDTO> { //Nome Anotação, TipodaClasse
	
	@Autowired
	private ControleVeiculoVagaCarroRepository repo;
	
	@Override
	public void initialize(ControleVeiculoVagaCarroUpdate ann) { //programação de inicialização caso necessário
	}
	
	//isValid faz o retorno dizendo se os dados do objeto estão de acordo com a validação
	@Override
	public boolean isValid(ControleVeiculoVagaCarroDTO objDTO, ConstraintValidatorContext context) {

		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		ControleVeiculoVagaCarro auxNome = repo.buscaControleVeiculo(objDTO.getCarroId());

		if (auxNome == null ) {
			list.add(new FieldMessage("carroId","Veiculo não entrou no estacionamento"));
		}		

		for (FieldMessage e : list) { //percorre a lista de erro e adiciona o erro personalizado na lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); // se a lista não retornar nenhum erro isValid vai retornar true.
	}
}
