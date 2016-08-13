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

import com.frostworks.apptreinamento.model.Atendente;
import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.model.TipoTreinamento;
import com.frostworks.apptreinamento.repository.filter.AtendenteFilter;

public class Atendentes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Atendente salvar(Atendente atendente) {
		return manager.merge(atendente);
	}

	public List<Atendente> todos() {
		return manager.createQuery("from Atendente p", Atendente.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Atendente> porAgencia(Long idAgencia) {
		Query query = manager.createQuery("from Atendente p where p.agencia.idAgencia = ? order by nome asc",
				Atendente.class);
		query.setParameter(1, idAgencia);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Atendente> porRegional(Long idRegional) {
		Query query = manager.createQuery(
				"from Atendente p where p.agencia.baseRegional.regional.idRegional = ? order by nome asc",
				Atendente.class);
		query.setParameter(1, idRegional);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Atendente> filtroPorRegional(Long idRegional) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Atendente.class)
				.createAlias("agencia", "a")
				.createAlias("a.regional", "r");

		if (idRegional != null) {
			criteria.add(Restrictions.eq("r.idRegional", idRegional));
		}

		return criteria.addOrder(Order.asc("nome")).list();

	}

	@SuppressWarnings("unchecked")
	public List<Atendente> porNomeAtivos(String nome, Long idEmpresa, TipoTreinamento tipo, Long idRegional) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Atendente.class)
				.createAlias("empresa", "e")
				.createAlias("agencia", "a")
				.createAlias("a.regional", "r");
		criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		criteria.add(Restrictions.eq("e.id", idEmpresa));
		criteria.add(Restrictions.eq("a.regional.id", idRegional));
		if (tipo.equals(TipoTreinamento.INICIAL)) {
			criteria.add(Restrictions.eq("matricula", ""));
		} else {
			criteria.add(Restrictions.isNotNull("matricula"));
		}
		criteria.add(Restrictions.eq("situacao", Situacao.ATIVO));
		List<Atendente> atendentes = criteria.addOrder(Order.asc("nome")).list();
		return atendentes;
	}

	@SuppressWarnings("unchecked")
	public List<Atendente> filtrados(AtendenteFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Atendente.class).createAlias("agencia", "a")
				.createAlias("cargo", "c").createAlias("empresa", "e");

		if (StringUtils.isNotBlank(filtro.getMatricula())) {
			criteria.add(Restrictions.eq("matricula", filtro.getMatricula().toUpperCase()));
		}

		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getAgencia())) {
			criteria.add(Restrictions.ilike("a.nome", filtro.getAgencia(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getCargo())) {
			criteria.add(Restrictions.ilike("c.nome", filtro.getCargo(), MatchMode.ANYWHERE));
		}

		if (filtro.getEmpresas() != null && filtro.getEmpresas().length > 0) {
			criteria.add(Restrictions.in("e.id", filtro.getEmpresas()));
		}

		if (filtro.getSituacoes() != null && filtro.getSituacoes().length > 0) {
			criteria.add(Restrictions.in("situacao", filtro.getSituacoes()));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

	public void adicionar(Atendente atendente) {
		manager.persist(atendente);
	}

	public Atendente getAtendente(Long pk) {
		return manager.getReference(Atendente.class, pk);
	}

	public Atendente porId(Long pk) {
		return manager.find(Atendente.class, pk);
	}

	public void excluir(Atendente atendente) {
		manager.remove(atendente);
	}

	public Atendente porLogin(String matAtendente, Long idEmpresa, Long idRegional) {
		return manager.createQuery("from Atendente a "
				+ "where a.empresa.id = :idempresa "
				+ "and a.agencia.regional.id = :idregional "
				+ "and a.matricula = :matricula", Atendente.class)
				.setParameter("matricula", matAtendente)
				.setParameter("idregional", idRegional)
				.setParameter("idempresa", idEmpresa)
				.getSingleResult();
	}

}
