package com.frostworks.apptreinamento.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory fabrica;
	
	static {
		fabrica = Persistence.createEntityManagerFactory("TreinamentoPU");
	}
	
	public static EntityManager getEntityManager() {
		return fabrica.createEntityManager();
	}
	
}
