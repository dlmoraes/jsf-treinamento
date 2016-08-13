package com.frostworks.apptreinamento.event;

import com.frostworks.apptreinamento.model.Treinamento;

public class TreinamentoAlteradoEvent {

	private Treinamento treinamento;

	public TreinamentoAlteradoEvent(Treinamento treinamento) {
		this.treinamento = treinamento;
	}

	public Treinamento getTreinamento() {
		return treinamento;
	}

}
