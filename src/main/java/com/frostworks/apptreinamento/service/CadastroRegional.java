package com.frostworks.apptreinamento.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Regional;
import com.frostworks.apptreinamento.repository.Regionais;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class CadastroRegional implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Regionais repositorio;

	@Transacional
	public void salvar(Regional regional) throws NegocioException {
		if (regional.getId() == null) {
			regional.setDtaCriacao(new Date());
		} else {
			regional.setDtaModificacao(new Date());
		}
		repositorio.salvar(regional);
	}

	@Transacional
	public Regional excluir(Regional regional) throws NegocioException {
		try {
			regional = repositorio.porId(regional.getId());
			if (regional != null) {
				repositorio.excluir(regional);

			}
			return regional;
		} catch (Exception ex) {
			throw new NegocioException("Erro ao excluir a regional.");
		}
	}

}