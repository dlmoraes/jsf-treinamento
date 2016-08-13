package com.frostworks.apptreinamento.model;

public enum SituacaoTreinamento {

	EMANDAMENTO("Em andamento"), CONCLUIDO("Concluído"), CANCELADO("Cancelado");

	private String descricao;

	SituacaoTreinamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
