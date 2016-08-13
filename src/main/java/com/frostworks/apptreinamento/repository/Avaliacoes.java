package com.frostworks.apptreinamento.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.frostworks.apptreinamento.model.Avaliacao;

public class Avaliacoes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Avaliacao salvar(Avaliacao avaliacao) {
		return manager.merge(avaliacao);
	}
	
	public List<Avaliacao> todas() {
		return manager.createQuery("from Avaliacao", Avaliacao.class).getResultList();
	}
	
	public Avaliacao porMatricula(Long idMatricula) {
		return manager.createQuery("from Avaliacao a where a.matricula.id = :idMatricula", Avaliacao.class)
				.setParameter("idMatricula", idMatricula)
				.getSingleResult();
	}
	
	public void adicionar(Avaliacao avaliacao) {
		manager.persist(avaliacao);
	}
	
	public Avaliacao getAvaliacao(Long pk) {
		return manager.getReference(Avaliacao.class, pk);
	}
	
	public Avaliacao porId(Long pk) {
		return manager.find(Avaliacao.class, pk);
	}
	
	public void excluir(Avaliacao avaliacao) {
		manager.remove(avaliacao);
	}

}
