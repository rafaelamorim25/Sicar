package services;

import domain.Lancamento;
import repositories.LancamentoRepository;

public class LancamentosService {
	
	LancamentoRepository lancamentoRepository = new LancamentoRepository();
	
	public String insert(Lancamento lancamento) {
		
		int inserted = lancamentoRepository.save(lancamento);
		
		if(inserted == 0) {
			return "O lançamento não foi inserido";
		}else if (inserted == 1) {
			return "Lançamento inserido com sucesso";
		}else {
			return "Varios lancamentos foram inseridos";
		}
	}
	
	public String delete(int id) {
		
		Lancamento lancamento = lancamentoRepository.findBy(id);
		
		int deleted = lancamentoRepository.delete(lancamento);
		
		if(deleted == 0) {
			return "O lançamento não foi deletado";
		}else if (deleted == 1) {
			return "Lançamento deletado com sucesso";
		}else {
			return "Varios lancamentos foram deletados";
		}
	}
	
	public Lancamento findBy(Integer id) {
		return lancamentoRepository.findBy(id);
	}
	
	public String upadte(Lancamento lancamento) {
		
		int deleted = lancamentoRepository.update(lancamento);
		
		if(deleted == 0) {
			return "O lançamento não foi atualizado";
		}else if (deleted == 1) {
			return "Lançamento atualizado com sucesso";
		}else {
			return "Varios lancamentos foram atualizados";
		}
	}
}
