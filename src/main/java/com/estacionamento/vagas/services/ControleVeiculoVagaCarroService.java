package com.estacionamento.vagas.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.vagas.domain.ControleVeiculoVagaCarro;
import com.estacionamento.vagas.domain.Relatorio;
import com.estacionamento.vagas.domain.VagaCarro;
import com.estacionamento.vagas.domain.Veiculo;
import com.estacionamento.vagas.domain.enums.StatusVaga;
import com.estacionamento.vagas.domain.enums.TipoVeiculo;
import com.estacionamento.vagas.dto.ControleVeiculoVagaCarroDTO;
import com.estacionamento.vagas.dto.ControleVeiculoVagaCarroNewDTO;
import com.estacionamento.vagas.repositories.ControleVeiculoVagaCarroRepository;
import com.estacionamento.vagas.repositories.VagaCarroRepository;
import com.estacionamento.vagas.services.exception.ObjectNotFoundException;

@Service
public class ControleVeiculoVagaCarroService {

	// Ele  tem que chamar o obj de acesso a dados uma dependencia
	@Autowired //quando uma dependencia e declada com Autowired ela vai ser instanciada pela spring
	private ControleVeiculoVagaCarroRepository repo;
	
	@Autowired
	private VagaCarroRepository vagaCarroRepository;
	
	@Autowired
	private VagaCarroService vagaCarroService;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private RelatorioService relatorioService;
	
	public ControleVeiculoVagaCarro buscarId(Integer id) {
		Optional<ControleVeiculoVagaCarro> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( //se a busca retorne null
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ControleVeiculoVagaCarro.class.getName()
			));
	}
	
	//Inserindo obj novo
		@Transactional
		public ControleVeiculoVagaCarro insert(ControleVeiculoVagaCarro obj) {
			
			repo.save(obj);
			
			VagaCarro vagc = vagaCarroService.buscar(obj.getVagaCarro().getId());
			
			vagc.setStatusVaga(StatusVaga.toEnum(2));

			vagaCarroRepository.save(vagc);
			
			return obj;
		}
		
		public ControleVeiculoVagaCarro fromDTO(ControleVeiculoVagaCarroNewDTO objDTO) {
			
			VagaCarro vagc = new VagaCarro(objDTO.getVagaCarroId(), null, null);
			Veiculo v = new Veiculo(objDTO.getVeiculoId(), null, null, null, null, null);
			
			ControleVeiculoVagaCarro cvc = new ControleVeiculoVagaCarro(vagc, v, new Date());
			
			return cvc;
		}
		
		@Transactional
		public ControleVeiculoVagaCarro update(ControleVeiculoVagaCarro obj) {
			ControleVeiculoVagaCarro newObj = repo.buscaControleVeiculo(obj.getVeiculo().getId());
			
			updateData(newObj, obj);
			
			repo.save(newObj);
			
			Relatorio rel = new Relatorio(null, newObj.getEntrada(), newObj.getSaida(), newObj.getVagaCarro().getId(), newObj.getVeiculo());
			
			rel.setTipoVeiculo(TipoVeiculo.toEnum(newObj.getVeiculo().getTipoVeiculo().getCod()));
			
			relatorioService.insert(rel);
			
			VagaCarro vagc = newObj.getVagaCarro();
			
			vagc.setStatusVaga(StatusVaga.DISPONIVEL);
			
			vagc.setControle(null);
			
			vagaCarroRepository.save(vagc);
			
			return newObj;
		}
		
		//methodo auxiliar
		private void updateData(ControleVeiculoVagaCarro newObj, ControleVeiculoVagaCarro obj) {
			newObj.setSaida(new Date());
		}
		
		public ControleVeiculoVagaCarro fromDTO(ControleVeiculoVagaCarroDTO objDTO) {
			Veiculo v = veiculoService.buscar(objDTO.getCarroId());
			return new ControleVeiculoVagaCarro(null, v, null);
		}
		
		
}
