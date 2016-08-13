package com.frostworks.apptreinamento.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.frostworks.apptreinamento.model.Empresa;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Empresa salvar(Empresa empresa) {
		return this.manager.merge(empresa);
	}

	public List<Empresa> todas() {
		return this.manager.createQuery("from Empresa e", Empresa.class).getResultList();
	}

	public boolean validarNome(String nomeEmpresa) {
		List<Empresa> resultado = this.manager.createQuery("from Empresa e where e.nome = :nomeEmpresa", Empresa.class)
				.setParameter("nomeEmpresa", nomeEmpresa).getResultList();
		return resultado.isEmpty();
	}

	public boolean validarSigla(String siglaEmpresa) {
		List<Empresa> resultado = this.manager
				.createQuery("from Empresa e where e.sigla = :siglaEmpresa", Empresa.class)
				.setParameter("siglaEmpresa", siglaEmpresa).getResultList();
		return resultado.isEmpty();
	}

	public Empresa porId(Long id) {
		return this.manager.find(Empresa.class, id);
	}

	public void excluir(Empresa empresa) {
		this.manager.remove(empresa);
	}

}
