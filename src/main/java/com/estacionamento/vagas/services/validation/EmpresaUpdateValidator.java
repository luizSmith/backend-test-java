package com.estacionamento.vagas.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.estacionamento.vagas.domain.Empresa;
import com.estacionamento.vagas.dto.EmpresaDTO;
import com.estacionamento.vagas.repositories.EmpresaRepository;
import com.estacionamento.vagas.resource.exception.FieldMessage;

public class EmpresaUpdateValidator implements ConstraintValidator<EmpresaUpdate, EmpresaDTO> { //Nome Anotação, TipodaClasse
	
	@Autowired
	private EmpresaRepository repo;
	
	@Autowired
	private HttpServletRequest request; //pega os atributos de URI
	
	@Override
	public void initialize(EmpresaUpdate ann) { //programação de inicialização caso necessário
	}
	
	//isValid faz o retorno dizendo se os dados do objeto estão de acordo com a validação
	@Override
	public boolean isValid(EmpresaDTO objDTO, ConstraintValidatorContext context) {
		//Estrutura de dados coleção de pares chave,valor
		// != mapList
		// (Map<String,String>) converte para o tipo necessário
		@SuppressWarnings("unchecked") //retira a marca de atenção
		Map<String,String> map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		Empresa auxNome = repo.findByNome(objDTO.getNome());
		//verifica se existe algum registro com esse nome diferente do autor atual.
		if (auxNome != null && !auxNome.getId().equals(uriId)) {
			list.add(new FieldMessage("nome","Nome já existente"));
		}		

		for (FieldMessage e : list) { //percorre a lista de erro e adiciona o erro personalizado na lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); // se a lista não retornar nenhum erro isValid vai retornar true.
	}
}
