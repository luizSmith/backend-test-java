package com.estacionamento.vagas.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.estacionamento.vagas.domain.Empresa;
import com.estacionamento.vagas.dto.EmpresaNewDTO;
import com.estacionamento.vagas.repositories.EmpresaRepository;
import com.estacionamento.vagas.resource.exception.FieldMessage;

public class EmpresaInsertValidator implements ConstraintValidator<EmpresaInsert, EmpresaNewDTO> { //Nome Anotação, TipodaClasse
	
	@Autowired
	private EmpresaRepository repo;
	
	@Override
	public void initialize(EmpresaInsert ann) { //programação de inicialização caso necessário
	}
	
	//isValid faz o retorno dizendo se os dados do objeto estão de acordo com a validação
	@Override
	public boolean isValid(EmpresaNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		Empresa auxNome = repo.findByNome(objDTO.getNome());
		if (auxNome != null) {
			list.add(new FieldMessage("nome","Nome já existente"));
		}
		
		Empresa auxCNPJ = repo.findByCnpj(objDTO.getCnpj());
		if (auxCNPJ != null) {
			list.add(new FieldMessage("cnpj", "CNPJ já existe"));
		}
		
		Optional<Empresa> auxId = repo.findById(1);
		if (auxId.isPresent()) {
			list.add(new FieldMessage("id", "Só pode haver uma Empresa"));
		}

		for (FieldMessage e : list) { //percorre a lista de erro e adiciona o erro personalizado na lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); // se a lista não retornar nenhum erro isValid vai retornar true.
	}
}
