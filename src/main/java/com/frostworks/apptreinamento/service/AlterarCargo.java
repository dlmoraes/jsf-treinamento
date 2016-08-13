package com.frostworks.apptreinamento.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Atendente;
import com.frostworks.apptreinamento.repository.Atendentes;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class AlterarCargo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Atendentes atendentes;
	
	@Transacional
	public Atendente alterarCargo(Atendente atendente) {
		
		if (atendente.isNaoAlteravel()) {
			throw new NegocioException("O cargo não pode ser alterado, quando o atendente está na situação "
					+ atendente.getSituacao().getDescricao() + ".");
		}
		
		atendente = atendentes.salvar(atendente);
		
		return atendente;
		
	}

}
