package com.frostworks.apptreinamento.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.frostworks.apptreinamento.model.SituacaoTreinamento;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.Treinamentos;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class GestaoTreinamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Treinamentos repositorio;

	@Transacional
	public Treinamento salvar(Treinamento treinamento) {

		if (treinamento.getId() == null) {
			treinamento.setDtaCriacao(new Date());
			treinamento.setSituacao(SituacaoTreinamento.EMANDAMENTO);
		} else {
			treinamento.setDtaModificacao(new Date());
			if (treinamento.getDtaFim() != null) {
				treinamento.setSituacao(SituacaoTreinamento.CONCLUIDO);
			}
		}

		treinamento = repositorio.salvar(treinamento);

		return treinamento;

	}

	@Transacional
	public Treinamento excluir(Treinamento treinamento) throws NegocioException {
		try {
			treinamento = repositorio.porId(treinamento.getId());
			if (treinamento != null) {
				repositorio.excluir(treinamento);
			}
			return treinamento;
		} catch (Exception ex) {
			throw new NegocioException("Erro ao excluir a treinamento.");
		}
	}

}
