package com.frostworks.apptreinamento.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Atendente;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.repository.Atendentes;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class CadastroAtendente implements Serializable {

	private static final long serialVersionUID = -5730563599589397748L;

	@Inject
	private Atendentes repositorio;

	@Transacional
	public Atendente salvar(Atendente atendente) throws NegocioException {
		if (atendente.getId() == null) {
			atendente.setDtaCriacao(new Date());
			atendente.setMatricula(atendente.getMatricula().toUpperCase());
		} else {
			atendente.setDtaModificacao(new Date());
		}
		
		atendente.setEmpresa(atendente.getAgencia().getEmpresa());
		
		atendente = repositorio.salvar(atendente);
		return atendente;

	}

	@Transacional
	public Atendente excluir(Atendente atendente) throws NegocioException {
		try {
			atendente = repositorio.porId(atendente.getId());
			if (atendente != null) {
				repositorio.excluir(atendente);
			}
			return atendente;
		} catch (Exception ex) {
			throw new NegocioException("Erro ao excluir a atendente.");
		}
	}

}
