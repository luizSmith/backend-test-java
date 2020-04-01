package com.estacionamento.vagas.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.estacionamento.vagas.domain.Veiculo;
import com.estacionamento.vagas.dto.VeiculoNewDTO;
import com.estacionamento.vagas.repositories.VeiculoRepository;
import com.estacionamento.vagas.resource.exception.FieldMessage;

public class VeiculoInsertValidator implements ConstraintValidator<VeiculoInsert, VeiculoNewDTO> { //Nome Anotação, TipodaClasse
	
	@Autowired
	private VeiculoRepository repo;
	
	@Override
	public void initialize(VeiculoInsert ann) { //programação de inicialização caso necessário
	}
	
	//isValid faz o retorno dizendo se os dados do objeto estão de acordo com a validação
	@Override
	public boolean isValid(VeiculoNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		Veiculo auxPlaca = repo.findByPlaca(objDTO.getPlaca());
		if (auxPlaca != null) {
			list.add(new FieldMessage("placa","Placa já existente"));
		}
		
		for (FieldMessage e : list) { //percorre a lista de erro e adiciona o erro personalizado na lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); // se a lista não retornar nenhum erro isValid vai retornar true.
	}
}
