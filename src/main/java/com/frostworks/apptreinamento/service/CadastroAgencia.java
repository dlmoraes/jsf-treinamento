package com.frostworks.apptreinamento.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Agencia;
import com.frostworks.apptreinamento.repository.Agencias;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class CadastroAgencia  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Agencias repositorio;

	@Transacional
	public Agencia salvar(Agencia agencia) throws NegocioException {
		if (agencia.getId() == null) {
			agencia.setDtaCriacao(new Date());
		} else {
			agencia.setDtaModificacao(new Date());
		}
		return repositorio.salvar(agencia);
	}

	@Transacional
	public Agencia excluir(Agencia agencia) throws NegocioException {
		try {
			agencia = repositorio.porId(agencia.getId());
			if (agencia != null) {
				repositorio.excluir(agencia);

			}
			return agencia;
		} catch (Exception ex) {
			throw new NegocioException("Erro ao excluir a agencia.");
		}
	}
}