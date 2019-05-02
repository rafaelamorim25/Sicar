package services;

import java.util.Date;
import java.util.List;

import domain.Cliente;
import repositories.ClienteRepository;
import repositories.RelatoriosRepository;

public class RelatoriosService {
	
	RelatoriosRepository relatoriosRepository = new RelatoriosRepository();
	ClienteRepository clienteRepository = new ClienteRepository();
	
	public Float amountToReceive() {
		List<Cliente> clientes = clienteRepository.findAll();
		float total = 0.0F;
		
		for(Cliente cliente : clientes) {
			total += cliente.saldo();
		}
		
		return total * (-1);
	}
	
	public Float amountReceivedBetween(Date a, Date b) {
		return relatoriosRepository.amountReceivedBetween(a, b);
	}
	
	public Float amountToReceiveBetween(Date a, Date b) {
		return relatoriosRepository.amountToReceiveBetween(a, b) * (-1);
	}
	
	public Float amountReceived() {
		return relatoriosRepository.amountReceived();
	}
}
