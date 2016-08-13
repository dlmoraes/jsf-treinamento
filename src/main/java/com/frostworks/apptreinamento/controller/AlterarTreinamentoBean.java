package com.frostworks.apptreinamento.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.frostworks.apptreinamento.event.LotacaoAlteradoEvent;
import com.frostworks.apptreinamento.event.LotacaoEdicao;
import com.frostworks.apptreinamento.model.Lotacao;
import com.frostworks.apptreinamento.service.AlterarTreinamento;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named
@RequestScoped
public class AlterarTreinamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AlterarTreinamento alterarTreinamento;
	
	@Inject
	@LotacaoEdicao
	private Lotacao lotacao;
	
	@Inject
	private Event<LotacaoAlteradoEvent> lotacaoAlteradoEvent;
	
	public void alterarTreinamento() {
		this.lotacao = this.alterarTreinamento.alterarTreinamentoLotacao(this.lotacao);
		
		this.lotacaoAlteradoEvent.fire(new LotacaoAlteradoEvent(this.lotacao));
		
		FacesUtil.addInfoMessage("O treinamento foi alterado com sucesso!");
	}

}
