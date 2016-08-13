package com.frostworks.apptreinamento.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.frostworks.apptreinamento.model.Agencia;
import com.frostworks.apptreinamento.repository.filter.AgenciaFilter;

public class Agencias implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Agencia salvar(Agencia agencia) {
		return manager.merge(agencia);
	}
	
	public List<Agencia> todas() {
		return manager.createQuery("from Agencia a order by a.nome", Agencia.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Agencia> porRegional(Long idRegional) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Agencia.class)
				.createAlias("b.regional", "r");
		criteria.add(Restrictions.eq("r.id", idRegional));
		return criteria.addOrder(Order.asc("nome")).list();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Agencia> filtrados(AgenciaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Agencia.class)
				.createAlias("regional", "r")
				.createAlias("empresa", "e");
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		
		if (StringUtils.isNotBlank(filtro.getNomeRegional())) {
			criteria.add(Restrictions.ilike("r.nome", filtro.getNomeRegional(), MatchMode.ANYWHERE));
		}
		
				
		if (filtro.getEmpresas() != null && filtro.getEmpresas().length > 0) {
			criteria.add(Restrictions.in("e.id", filtro.getEmpresas()));
		}
		
		if (filtro.getSituacoes() != null && filtro.getSituacoes().length > 0) {
			criteria.add(Restrictions.in("situacao", filtro.getSituacoes()));
		}	
		
		return criteria.addOrder(Order.asc("nome")).list();
		
	}
	
	public void adicionar(Agencia agencia) {
		manager.persist(agencia);
	}
	
	public Agencia getAgencia(Long pk) {
		return manager.getReference(Agencia.class, pk);
	}
	
	public Agencia porId(Long pk) {
		return manager.find(Agencia.class, pk);
	}
	
	public void excluir(Agencia agencia) {
		manager.remove(agencia);
	}


}
