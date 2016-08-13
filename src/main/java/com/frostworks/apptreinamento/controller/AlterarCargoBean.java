package com.frostworks.apptreinamento.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.frostworks.apptreinamento.event.AtendenteAlteradoEvent;
import com.frostworks.apptreinamento.event.AtendenteEdicao;
import com.frostworks.apptreinamento.model.Atendente;
import com.frostworks.apptreinamento.service.AlterarCargo;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named
@RequestScoped
public class AlterarCargoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AlterarCargo alterarCargo;

	@Inject
	@AtendenteEdicao
	private Atendente atendente;

	@Inject
	private Event<AtendenteAlteradoEvent> atendenteAlteradoEvent;

	public void alterarCargo() {
		this.atendente = this.alterarCargo.alterarCargo(this.atendente);

		this.atendenteAlteradoEvent.fire(new AtendenteAlteradoEvent(this.atendente));

		FacesUtil.addInfoMessage("O cargo do atendente " + this.atendente.getNome() + ", foi alterado com sucesso!");

	}

}
