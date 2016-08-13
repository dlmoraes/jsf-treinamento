package com.frostworks.apptreinamento.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.frostworks.apptreinamento.model.SituacaoTreinamento;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class EncerrarTreinamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private GestaoTreinamento gestao;

	@Transacional
	public Treinamento encerrarTreinamento(Treinamento treinamento) {
		
		if (treinamento.isNaoEncerravel()) {
			throw new NegocioException("Treinamento não pode ser encerrado, com a situação " 
		+ treinamento.getSituacao().getDescricao() + ".");
		}
		
		treinamento.setSituacao(SituacaoTreinamento.CONCLUIDO);
				
		treinamento = gestao.salvar(treinamento);
		
		return treinamento;
		
	}

}
