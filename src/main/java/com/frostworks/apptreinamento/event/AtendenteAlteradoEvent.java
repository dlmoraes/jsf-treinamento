package com.frostworks.apptreinamento.event;

import com.frostworks.apptreinamento.model.Atendente;

public class AtendenteAlteradoEvent {

	private Atendente atendente;

	public AtendenteAlteradoEvent(Atendente atendente) {
		this.atendente = atendente;
	}

	public Atendente getAtendente() {
		return atendente;
	}

}
