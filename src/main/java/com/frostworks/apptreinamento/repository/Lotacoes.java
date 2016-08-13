package com.frostworks.apptreinamento.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.frostworks.apptreinamento.model.Lotacao;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.repository.filter.LotacaoFilter;

public class Lotacoes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Lotacao salvar(Lotacao Lotacao) {
		return manager.merge(Lotacao);
	}
	
	public void lotar(List<Lotacao> lotacoes) {
		for (Lotacao lotacao : lotacoes) {
			manager.persist(lotacao);
		}
	}
	
	public List<Lotacao> todas() {
		return manager.createQuery("from Lotacao", Lotacao.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Lotacao> porSituacao(Situacao situacao) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Lotacao.class);
		criteria.add(Restrictions.eq("situacao", situacao));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Lotacao> filtrados(LotacaoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Lotacao.class)
				.createAlias("treinamento", "t");
//				.createAlias("atendente", "a");
		
//		if (StringUtils.isNotBlank(filtro.getNomeAtendente())) {
//			criteria.add(Restrictions.ilike("a.nome", filtro.getNomeAtendente(), MatchMode.ANYWHERE));
//		}
		
		if (StringUtils.isNotBlank(filtro.getTreinamento())) {
			criteria.add(Restrictions.ilike("t.titulo", filtro.getTreinamento(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getTipoTreinamentos() != null && filtro.getTipoTreinamentos().length > 0) {
			criteria.add(Restrictions.in("t.tipoTreinamento", filtro.getTipoTreinamentos()));
		}
		
		return criteria.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Lotacao> filtradosPorTreinamento(Long id) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Lotacao.class)
				.createAlias("treinamento", "t");
		
		if (id != null) {
			criteria.add(Restrictions.eq("t.idTreinamento", id));
		}
		
		return criteria.list();
		
	}
	
	public void adicionar(Lotacao Lotacao) {
		manager.persist(Lotacao);
	}
	
	public Lotacao getLotacao(Long pk) {
		return manager.getReference(Lotacao.class, pk);
	}
	
	public Lotacao porId(Long pk) {
		return manager.find(Lotacao.class, pk);
	}
	
	public void excluir(Lotacao Lotacao) {
		manager.remove(Lotacao);
	}
	

}
