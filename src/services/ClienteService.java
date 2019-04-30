package services;

import java.util.List;

import domain.Cliente;
import repositories.ClienteRepository;

public class ClienteService {
	
	ClienteRepository clienteRepository = new ClienteRepository();
	
	public void insert(Cliente cliente) throws Exception{
		clienteRepository.save(cliente);		
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public List<Cliente> customSearch(String keyword){
		return clienteRepository.customSearch(keyword);
	}
	
	public String delete(int id) {
		
		Cliente cliente = clienteRepository.findBy(id);
		if(cliente.saldo()==0) {
			int deleted = clienteRepository.delete(cliente);
			
			if(deleted == 1) {
				return "Registro apagado com sucesso";
			}else if(deleted > 1) {
				return "Foram apagados mais de 1 registro";
			}else {
				return "nenhum registro foi apagado";
			}
		}
		throw new RuntimeException("Não é possivel deletar cliente que está em divida");
		//return "Não é possivel deletar cliente que está em divida";
	}
	
	public Cliente findBy(int id) {
		return clienteRepository.findBy(id);
	}
	
	public Cliente findBy(Long cpf) {
		return clienteRepository.findBy(cpf);
	}
	
	public String update(Cliente cliente) {
		int deleted = clienteRepository.update(cliente);
		
		if(deleted == 0) {
			return "Cliente não foi atualizado";
		}else if(deleted == 1) {
			return "Cliente atualizado com sucesso";
		}else {
			return "Mais de um cliente foi atualizado";
		}
	}

}
