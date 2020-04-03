package com.estacionamento.vagas.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacionamento.vagas.domain.ControleVeiculoVagaMoto;
import com.estacionamento.vagas.domain.Relatorio;
import com.estacionamento.vagas.domain.VagaMoto;
import com.estacionamento.vagas.domain.Veiculo;
import com.estacionamento.vagas.domain.enums.StatusVaga;
import com.estacionamento.vagas.domain.enums.TipoVeiculo;
import com.estacionamento.vagas.dto.ControleVeiculoVagaMotoDTO;
import com.estacionamento.vagas.dto.ControleVeiculoVagaMotoNewDTO;
import com.estacionamento.vagas.repositories.ControleVeiculoVagaMotoRepository;
import com.estacionamento.vagas.repositories.VagaMotoRepository;
import com.estacionamento.vagas.services.exception.ObjectNotFoundException;

@Service
public class ControleVeiculoVagaMotoService {

	// Ele  tem que chamar o obj de acesso a dados uma dependencia
	@Autowired //quando uma dependencia e declada com Autowired ela vai ser instanciada pela spring
	private ControleVeiculoVagaMotoRepository repo;
	
	@Autowired
	private VagaMotoRepository vagaMotoRepository;
	
	@Autowired
	private VagaMotoService vagaMotoService;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private RelatorioService relatorioService;
	
	public ControleVeiculoVagaMoto buscarId(Integer id) {
		Optional<ControleVeiculoVagaMoto> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException( //se a busca retorne null
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ControleVeiculoVagaMoto.class.getName()
			));
	}
	
	//Inserindo obj novo
		@Transactional
		public ControleVeiculoVagaMoto insert(ControleVeiculoVagaMoto obj) {
			
			repo.save(obj);
			
			VagaMoto vagm = vagaMotoService.buscar(obj.getVagaMoto().getId());
			
			vagm.setStatusVaga(StatusVaga.toEnum(2));

			vagaMotoRepository.save(vagm);
			
			return obj;
		}
		
		public ControleVeiculoVagaMoto fromDTO(ControleVeiculoVagaMotoNewDTO objDTO) {
			
			VagaMoto vagc = new VagaMoto(objDTO.getVagaMotoId(), null, null);
			Veiculo v = new Veiculo(objDTO.getVeiculoId(), null, null, null, null, null);
			
			ControleVeiculoVagaMoto cvc = new ControleVeiculoVagaMoto(vagc, v, new Date());
			
			return cvc;
		}
		
		@Transactional
		public ControleVeiculoVagaMoto update(ControleVeiculoVagaMoto obj) {
			ControleVeiculoVagaMoto newObj = repo.buscaControleVeiculo(obj.getVeiculo().getId());
			
			updateData(newObj, obj);
			
			repo.save(newObj);
			
			Relatorio rel = new Relatorio(null, newObj.getEntrada(), newObj.getSaida(), newObj.getVagaMoto().getId(), newObj.getVeiculo());
			
			rel.setTipoVeiculo(TipoVeiculo.toEnum(newObj.getVeiculo().getTipoVeiculo().getCod()));
			
			relatorioService.insert(rel);
			
			VagaMoto vagm = newObj.getVagaMoto();
			vagm.setStatusVaga(StatusVaga.DISPONIVEL);
			
			vagm.setControle(null);
			
			vagaMotoRepository.save(vagm);
			
			return newObj;
		}
		
		//methodo auxiliar
		private void updateData(ControleVeiculoVagaMoto newObj, ControleVeiculoVagaMoto obj) {
			newObj.setSaida(new Date());
		}
		
		public ControleVeiculoVagaMoto fromDTO(ControleVeiculoVagaMotoDTO objDTO) {
			Veiculo v = veiculoService.buscar(objDTO.getMotoId());
			return new ControleVeiculoVagaMoto(null, v, null);
		}

}
