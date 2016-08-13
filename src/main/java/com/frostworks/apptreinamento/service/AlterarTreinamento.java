package com.frostworks.apptreinamento.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Lotacao;
import com.frostworks.apptreinamento.repository.Lotacoes;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class AlterarTreinamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Lotacoes lotacoes;
	
	@Transacional
	public Lotacao alterarTreinamentoLotacao(Lotacao lotacao) {
		
		if (lotacao.isTreinamentoNaoAlteravel()) {
			throw new NegocioException("O treinamento não pode ser alterado!");
		}
		
		lotacao = this.lotacoes.salvar(lotacao);
		
		return lotacao;
	}

}
