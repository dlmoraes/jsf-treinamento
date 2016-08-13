package com.frostworks.apptreinamento.event;

import java.io.Serializable;

import com.frostworks.apptreinamento.model.Lotacao;

public class LotacaoAlteradoEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Lotacao lotacao;

	public LotacaoAlteradoEvent(Lotacao lotacao) {
		this.lotacao = lotacao;
	}

	public Lotacao getLotacao() {
		return lotacao;
	}

}
