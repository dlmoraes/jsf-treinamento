package com.frostworks.apptreinamento.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.frostworks.apptreinamento.model.Regional;

public class Regionais implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Regional salvar(Regional regional) {
		return manager.merge(regional);
	}

	public List<Regional> todas() {
		return manager.createQuery("from Regional order by nome", Regional.class).getResultList();
	}

	public List<Regional> porEmpresa(Long idEmpresa) {
		return manager.createQuery(
				"from Regional r where r.empresa.id = :idEmpresa order by r.nome",
				Regional.class).setParameter("idEmpresa", idEmpresa).getResultList();
	}

	public void adicionar(Regional regional) {
		manager.persist(regional);
	}

	public Regional getRegional(Long pk) {
		return manager.getReference(Regional.class, pk);
	}

	public Regional porId(Long pk) {
		return manager.find(Regional.class, pk);
	}

	public void excluir(Regional regional) {
		manager.remove(regional);
	}

}
