package com.frostworks.apptreinamento.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.frostworks.apptreinamento.model.Avaliacao;
import com.frostworks.apptreinamento.model.Matricula;
import com.frostworks.apptreinamento.model.SituacaoAvaliacao;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.Avaliacoes;
import com.frostworks.apptreinamento.repository.Matriculas;
import com.frostworks.apptreinamento.repository.Treinamentos;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class GestaoAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Avaliacoes repositorio;
	
	@Inject
	private Matriculas matriculas;
	
	@Inject
	private Treinamentos treinamentos;

	@Transacional
	public Avaliacao salvar(Avaliacao avaliacao) throws NegocioException {
		try {
			if (avaliacao.getId() == null) {
				avaliacao.setDtaCriacao(new Date());
			} else {
				avaliacao.setDtaModificacao(new Date());
			}

			avaliacao = repositorio.salvar(avaliacao);
			
			boolean resultado = avaliacao.isAprovado();
			
			Matricula matricula = avaliacao.getMatricula();
			
			if (avaliacao.getNota1() < 8 && (avaliacao.getNota2() == null || avaliacao.getNota2() == 0.00)) {
				matricula.setResultado(SituacaoAvaliacao.NAOAVALIADO);
			} else if ((StringUtils.isNotBlank(avaliacao.getArquivoNota1()) || StringUtils.isNotEmpty(avaliacao.getArquivoNota1()))
					|| (StringUtils.isNotBlank(avaliacao.getArquivoNota2()) || StringUtils.isNotEmpty(avaliacao.getArquivoNota2()))) {
				matricula.setResultado(SituacaoAvaliacao.PENDENTE);
			} else {
				matricula.setResultado(resultado ? SituacaoAvaliacao.APROVADO : SituacaoAvaliacao.REPROVADO);
			}
			
			avaliacao.setMatricula(matricula);
			
			matriculas.salvar(avaliacao.getMatricula());
			
			return avaliacao;

		} catch (Exception ex) {
			throw new NegocioException("Não foi possível realizar esta operação.");
		}

	}

	@Transacional
	public void excluir(Avaliacao avaliacao) throws NegocioException {
		try {
			avaliacao = repositorio.porId(avaliacao.getId());
			if (avaliacao != null) {
				repositorio.excluir(avaliacao);
			}
		} catch (Exception ex) {
			throw new NegocioException("Não foi possível realizar esta operação.");
		}
	}

}
