package com.frostworks.apptreinamento.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.frostworks.apptreinamento.model.Matricula;
import com.frostworks.apptreinamento.service.NegocioException;

public class Matriculas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Matricula salvar(Matricula matricula) {
		return this.manager.merge(matricula);
	}

	public List<Matricula> todas() {
		return this.manager.createQuery("from Matricula m", Matricula.class).getResultList();
	}

	public Matricula porId(Long id) {
		return this.manager.find(Matricula.class, id);
	}

	public void excluir(Matricula matricula) {
		this.manager.remove(matricula);
	}
	
	public List<Matricula> porLotacao(Long idLotacao) {
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Matricula.class)
				.createAlias("lotacao", "l")
				.createAlias("atendente", "a");
		if (idLotacao == null) {
			throw new NegocioException("Lotação inválida/incorreta");
		} 
		
		criteria.add(Restrictions.eq("l.id", idLotacao));
		return criteria.addOrder(Order.asc("a.nome")).list();
	}

}
