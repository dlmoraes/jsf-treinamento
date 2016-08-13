package com.frostworks.apptreinamento.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.frostworks.apptreinamento.model.Cargo;
import com.frostworks.apptreinamento.model.Situacao;

public class Cargos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Cargo salvar(Cargo cargo) {
		return this.manager.merge(cargo);
	}
	
	public List<Cargo> todos() {
		return this.manager.createQuery("from Cargo", Cargo.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cargo> filtrado(String cargo) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cargo.class);
		criteria.add(Restrictions.ilike("nome", cargo, MatchMode.START));
		return criteria.list();
	}
	
	public Cargo getCargo(Long pk) {
		return this.manager.getReference(Cargo.class, pk);
	}

	public Cargo porId(Long pk) {
		return this.manager.find(Cargo.class, pk);
	}
	
	public void excluir(Cargo cargo) {
		this.manager.remove(cargo);
	}

}
