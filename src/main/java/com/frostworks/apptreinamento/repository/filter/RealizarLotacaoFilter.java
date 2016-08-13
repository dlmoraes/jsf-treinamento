package com.frostworks.apptreinamento.repository.filter;

import java.io.Serializable;

import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.Regional;
import com.frostworks.apptreinamento.model.TipoTreinamento;

public class RealizarLotacaoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Regional regionalSelecionada;
	private Empresa empresaSelecionada;
	private TipoTreinamento tipo;

	public TipoTreinamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoTreinamento tipo) {
		this.tipo = tipo;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}

	public boolean isInicialSelecionado() {
		if (tipo == null) {
			return false;
		}
		return this.tipo.equals(TipoTreinamento.INICIAL);
	}

	public Regional getRegionalSelecionada() {
		return regionalSelecionada;
	}

	public void setRegionalSelecionada(Regional regionalSelecionada) {
		this.regionalSelecionada = regionalSelecionada;
	}

}
