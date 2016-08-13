package com.frostworks.apptreinamento.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory fabrica;
	
	public EntityManagerProducer() {
		this.fabrica = Persistence.createEntityManagerFactory("TreinamentoPU");
	}
	
	@Produces
	@RequestScoped
	public EntityManager criarEntityManager() {
		return this.fabrica.createEntityManager();
	}
	
}
