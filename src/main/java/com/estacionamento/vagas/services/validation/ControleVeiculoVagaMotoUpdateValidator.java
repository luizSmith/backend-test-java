package com.estacionamento.vagas.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.estacionamento.vagas.domain.ControleVeiculoVagaMoto;
import com.estacionamento.vagas.dto.ControleVeiculoVagaMotoDTO;
import com.estacionamento.vagas.repositories.ControleVeiculoVagaMotoRepository;
import com.estacionamento.vagas.resource.exception.FieldMessage;

public class ControleVeiculoVagaMotoUpdateValidator implements ConstraintValidator<ControleVeiculoVagaMotoUpdate, ControleVeiculoVagaMotoDTO> { //Nome Anotação, TipodaClasse
	
	@Autowired
	private ControleVeiculoVagaMotoRepository repo;
	
	@Override
	public void initialize(ControleVeiculoVagaMotoUpdate ann) { //programação de inicialização caso necessário
	}
	
	//isValid faz o retorno dizendo se os dados do objeto estão de acordo com a validação
	@Override
	public boolean isValid(ControleVeiculoVagaMotoDTO objDTO, ConstraintValidatorContext context) {

		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		ControleVeiculoVagaMoto auxNome = repo.buscaControleVeiculo(objDTO.getMotoId());

		if (auxNome == null ) {
			list.add(new FieldMessage("motoId","Veiculo não entrou no estacionamento"));
		}		

		for (FieldMessage e : list) { //percorre a lista de erro e adiciona o erro personalizado na lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); // se a lista não retornar nenhum erro isValid vai retornar true.
	}
}
