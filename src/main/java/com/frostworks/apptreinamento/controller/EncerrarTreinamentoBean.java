package com.frostworks.apptreinamento.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.frostworks.apptreinamento.event.TreinamentoAlteradoEvent;
import com.frostworks.apptreinamento.event.TreinamentoEdicao;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.service.EncerrarTreinamento;
import com.frostworks.apptreinamento.service.NegocioException;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named
@RequestScoped
public class EncerrarTreinamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EncerrarTreinamento encerrarTreinamento;

	@Inject
	@TreinamentoEdicao
	private Treinamento treinamento;

	@Inject
	private Event<TreinamentoAlteradoEvent> treinamentoAlteradoEvent;

	public void encerrarTreinamento() {
		try {
			this.treinamento = this.encerrarTreinamento.encerrarTreinamento(this.treinamento);

			this.treinamentoAlteradoEvent.fire(new TreinamentoAlteradoEvent(this.treinamento));

			FacesUtil.addInfoMessage("Treinamento encerrado com sucesso!");
		} catch (Exception ex) {
			throw new NegocioException("Não foi possível realizar esta operação.");
		}
	}

}
