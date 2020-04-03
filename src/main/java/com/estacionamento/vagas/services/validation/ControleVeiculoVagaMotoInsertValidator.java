package com.estacionamento.vagas.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.estacionamento.vagas.domain.ControleVeiculoVagaMoto;
import com.estacionamento.vagas.domain.Veiculo;
import com.estacionamento.vagas.domain.enums.TipoVeiculo;
import com.estacionamento.vagas.dto.ControleVeiculoVagaMotoNewDTO;
import com.estacionamento.vagas.repositories.ControleVeiculoVagaMotoRepository;
import com.estacionamento.vagas.repositories.VeiculoRepository;
import com.estacionamento.vagas.resource.exception.FieldMessage;

public class ControleVeiculoVagaMotoInsertValidator implements ConstraintValidator<ControleVeiculoVagaMotoInsert, ControleVeiculoVagaMotoNewDTO> { //Nome Anotação, TipodaClasse
	
	@Autowired
	private ControleVeiculoVagaMotoRepository repo;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Override
	public void initialize(ControleVeiculoVagaMotoInsert ann) { //programação de inicialização caso necessário
	}
	
	//isValid faz o retorno dizendo se os dados do objeto estão de acordo com a validação
	@Override
	public boolean isValid(ControleVeiculoVagaMotoNewDTO objDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		
		ControleVeiculoVagaMoto auxVaga = repo.buscaControle(objDTO.getVagaMotoId());
				
		ControleVeiculoVagaMoto auxVeiculo = repo.buscaControleVeiculo(objDTO.getVeiculoId());
		
		Veiculo v = veiculoRepository.getOne(objDTO.getVeiculoId());
		
		if (v.getTipoVeiculo().equals(TipoVeiculo.CARRO)) {
			list.add(new FieldMessage("vagaMotoId","Não pode colocar um Carro numa vaga de Moto"));
		}
		
		if (auxVeiculo != null) {
			list.add(new FieldMessage("veiculoId","Veiculo já está no estabelecimento"));
		}		
		
		if (auxVaga != null) {
			list.add(new FieldMessage("vagaMotoId","Vaga já está sendo utilizada"));
		}
		
		for (FieldMessage e : list) { //percorre a lista de erro e adiciona o erro personalizado na lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty(); // se a lista não retornar nenhum erro isValid vai retornar true.
	}
}
