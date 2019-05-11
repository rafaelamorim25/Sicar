package domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Testes {

	public static void main(String[] args) {
		
		Teste t = new Teste();
		t.setDescrição("sei la");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		
		em.close();
		emf.close();

	}

}
