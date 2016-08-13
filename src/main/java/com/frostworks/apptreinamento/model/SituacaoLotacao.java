package com.frostworks.apptreinamento.model;

public enum SituacaoLotacao {

	ABERTA("Aberta"),
	EMANDAMENTO("Em Andamento"),
	FECHADA("Fechada"),
	CANCELADO("Cancelado");

	private String descricao;

	SituacaoLotacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
