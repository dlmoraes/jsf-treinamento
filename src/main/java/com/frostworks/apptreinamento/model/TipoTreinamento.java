package com.frostworks.apptreinamento.model;

public enum TipoTreinamento {

	INICIAL("Treinamento Inicial"), ROTINA("Treinamento de Rotina");

	private String descricao;

	TipoTreinamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
