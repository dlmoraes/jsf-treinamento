package com.frostworks.apptreinamento.model;

public enum SituacaoAvaliacao {

	APROVADO("Aprovado"), NAOAVALIADO("Não avaliado"), REPROVADO("Reprovado"), PENDENTE("Pendência");

	private String descricao;

	SituacaoAvaliacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
