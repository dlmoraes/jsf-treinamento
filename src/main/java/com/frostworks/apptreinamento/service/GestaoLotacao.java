package com.frostworks.apptreinamento.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Lotacao;
import com.frostworks.apptreinamento.model.SituacaoLotacao;
import com.frostworks.apptreinamento.repository.Lotacoes;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class GestaoLotacao implements Serializable {

	private static final long serialVersionUID = 5905589636456507363L;

	@Inject
	private Lotacoes repositorio;

	@Transacional
	public void salvar(Lotacao lotacao) throws NegocioException {
		if (lotacao.getId() == null) {
			lotacao.setDtaCriacao(new Date());
			lotacao.setSituacao(SituacaoLotacao.EMANDAMENTO);
			repositorio.adicionar(lotacao);
		} else {
			lotacao.setDtaModificacao(new Date());
			repositorio.salvar(lotacao);
		}

	}

	@Transacional
	public void excluir(Lotacao lotacao) throws NegocioException {
		lotacao = repositorio.porId(new Long(lotacao.getId().toString()));
		if (lotacao != null) {
			repositorio.excluir(lotacao);
		}
	}

}
