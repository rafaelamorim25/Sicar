package services;

import java.util.Date;

import repositories.ClienteRepository;
import repositories.RelatoriosRepository;

public class RelatoriosService {
	
	RelatoriosRepository relatoriosRepository = new RelatoriosRepository();
	ClienteRepository clienteRepository = new ClienteRepository();
	
	public Float amountToReceive() {
		return relatoriosRepository.amountToReceive();
	}
	
	public Float amountReceivedBetween(Date a, Date b) {
		return relatoriosRepository.amountReceivedBetween(a, b);
	}
	
	public Float amountToReceiveBetween(Date a, Date b) {
		return relatoriosRepository.amountToReceiveBetween(a, b);
	}
	
	public Float amountReceived() {
		return relatoriosRepository.amountReceived();
	}
	
	public Float amountPendency() {
		return this.amountToReceive() - this.amountReceived();
	}
	
	public Float amountPendency(Date a, Date b) {
		return this.amountToReceiveBetween(a, b) - amountReceivedBetween(a, b);
	}
}
