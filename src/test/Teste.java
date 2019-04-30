package test;

import java.util.List;

import domain.Cliente;
import repositories.ClienteRepository;

public class Teste {
	
	public static void main(String[] args) {
		
	ClienteRepository c = new ClienteRepository();
	
	List<Cliente> cl = c.customSearch("70423441115");
	
	System.out.println(cl.toString());
	}
}
