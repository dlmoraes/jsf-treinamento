package com.frostworks.apptreinamento.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.model.SituacaoLotacao;
import com.frostworks.apptreinamento.model.SituacaoTreinamento;
import com.frostworks.apptreinamento.model.TipoTreinamento;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.filter.TreinamentoFilter;

public class Treinamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Treinamento salvar(Treinamento treinamento) {
		return manager.merge(treinamento);
	}
	
	public List<Treinamento> todos() {
		return manager.createQuery("select p from Treinamento p", Treinamento.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Treinamento> porSituacao(Situacao situacao) {
		Query query = manager.createQuery("select p from Treinamento p where p.situacao = ?", Treinamento.class);
		query.setParameter(1, situacao);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Treinamento> porTipo(TipoTreinamento tipo, Situacao situacao) {
		Query query = manager.createQuery("from Treinamento p where p.tipoTreinamento = ? and p.situacao = ?", Treinamento.class);
		query.setParameter(1, tipo);
		query.setParameter(2, SituacaoLotacao.EMANDAMENTO);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Treinamento> porTipoeEmpresa(TipoTreinamento tipo, Long idEmpresa) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Treinamento.class)
				.createAlias("empresa", "e");
		criteria.add(Restrictions.eq("tipoTreinamento", tipo));
		criteria.add(Restrictions.eq("e.id", idEmpresa));
		criteria.add(Restrictions.eq("situacao", SituacaoTreinamento.EMANDAMENTO));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Treinamento> porTituloAtivos(String titulo) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Treinamento.class);
		criteria.add(Restrictions.ilike("titulo", titulo, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("situacao", Situacao.ATIVO));
		List<Treinamento> treinamentos = criteria.addOrder(Order.asc("titulo")).list();
		return treinamentos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Treinamento> porCodigoAtivos(String codTreinamento) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Treinamento.class);
		criteria.add(Restrictions.eq("codTreinamento", codTreinamento));
		criteria.add(Restrictions.eq("situacao", Situacao.ATIVO));
		List<Treinamento> treinamentos = criteria.addOrder(Order.asc("titulo")).list();
		return treinamentos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Treinamento> filtrados(TreinamentoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Treinamento.class)
				.createAlias("empresa", "e");
		
		if (StringUtils.isNotBlank(filtro.getTitulo())) {
			criteria.add(Restrictions.ilike("titulo", filtro.getTitulo(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getDeInicio() != null) {
			criteria.add(Restrictions.ge("dtaInicio", filtro.getDeInicio()));
		}
		
		if (filtro.getDeFim() != null) {
			criteria.add(Restrictions.le("dtaFim", filtro.getDeFim()));
		}
		
		if (filtro.getTipos() != null && filtro.getTipos().length > 0) {
			criteria.add(Restrictions.in("tipoTreinamento", filtro.getTipos()));
		}
		
		if (filtro.getSituacoes() != null && filtro.getSituacoes().length > 0) {
			criteria.add(Restrictions.in("situacao", filtro.getSituacoes()));
		}
		
		if (filtro.getEmpresas() != null && filtro.getEmpresas().length > 0) {
			criteria.add(Restrictions.in("e.id", filtro.getEmpresas()));
		}
		
		return criteria.addOrder(Order.asc("tipoTreinamento")).addOrder(Order.asc("titulo")).list();
	}
	
//	public List<Treinamento> por

	public void adicionar(Treinamento treinamento) {
		manager.persist(treinamento);
	}
	
	public Treinamento getTreinamento(Long pk) {
		return manager.getReference(Treinamento.class, pk);
	}
	
	public Treinamento porId(Long pk) {
		return manager.find(Treinamento.class, pk);
	}
	
	public void excluir(Treinamento treinamento) {
		manager.remove(treinamento);
	}

}
