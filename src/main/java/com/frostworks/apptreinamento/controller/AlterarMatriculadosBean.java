package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.model.Atendente;
import com.frostworks.apptreinamento.model.Lotacao;
import com.frostworks.apptreinamento.model.Matricula;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.model.TipoTreinamento;
import com.frostworks.apptreinamento.repository.Atendentes;
import com.frostworks.apptreinamento.service.GestaoLotacao;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("CadMatriculaMB")
@ViewScoped
public class AlterarMatriculadosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestaoLotacao gestaoLotacao;
	@Inject
	private Atendentes atendentes;
	private TipoTreinamento tipo;
	private Lotacao lotacao;
	private Atendente atendenteLinhaEditavel;
	private String matAtendente;

	public void inicializar() {
		this.lotacao.adicionarMatriculaVazia();
		this.tipo = this.lotacao.getTreinamento().getTipoTreinamento();
	}

	public void salvar() {
		try {
			this.lotacao.removerMatriculaVazia();
			this.gestaoLotacao.salvar(this.lotacao);
			FacesUtil.addInfoMessage("Lotação salva com sucesso!");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao salvar está lotação!");
		}
	}

	public void carregarAtendentePorLogin() {
		try {
			if (StringUtils.isNotBlank(this.matAtendente)) {
				System.out.println("Empresa selecionada: " + this.lotacao.getEmpresa().getId());
				this.atendenteLinhaEditavel = this.atendentes.porLogin(this.matAtendente.toUpperCase(),
						this.lotacao.getEmpresa().getId(), this.lotacao.getRegional().getId());
				this.carregarAtendenteLinhaEditavel();
			}
		} catch (NoResultException e) {
			FacesUtil.addErrorMessage("Atendente não encontrado! Favor, informar um novo login.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao realizar está operação.");
		}
	}

	public void carregarAtendenteLinhaEditavel() {
		Matricula matricula = this.lotacao.getMatriculas().get(0);

		if (this.atendenteLinhaEditavel != null) {
			if (this.existeMatriculaComAtendente(this.atendenteLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe o atendente informado, já consta na relação.");
			} else {
				matricula.setDtaCriacao(new Date());
				matricula.setAtendente(this.atendenteLinhaEditavel);
				matricula.setEmpresa(this.atendenteLinhaEditavel.getEmpresa());
				matricula.setSituacao(Situacao.ATIVO);

				this.lotacao.adicionarMatriculaVazia();
				this.atendenteLinhaEditavel = null;
				this.matAtendente = null;
			}
		}
	}

	public List<Atendente> completarAtendente(String nome) {

		try {
			return this.atendentes.porNomeAtivos(nome, lotacao.getEmpresa().getId(), tipo,
					lotacao.getRegional().getId());
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao carregar o atendente.");

		}
		return null;
	}

	private boolean existeMatriculaComAtendente(Atendente atendente) {
		boolean atendenteExiste = false;

		for (Matricula matricula : this.getLotacao().getMatriculas()) {
			if (atendente.equals(matricula.getAtendente())) {
				atendenteExiste = true;
				break;
			}
		}

		return atendenteExiste;
	}

	public Lotacao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
	}

	public TipoTreinamento getTipo() {
		return tipo;
	}

	public Atendente getAtendenteLinhaEditavel() {
		return atendenteLinhaEditavel;
	}

	public void setAtendenteLinhaEditavel(Atendente atendenteLinhaEditavel) {
		this.atendenteLinhaEditavel = atendenteLinhaEditavel;
	}

	public String getMatAtendente() {
		return matAtendente;
	}

	public void setMatAtendente(String matAtendente) {
		this.matAtendente = matAtendente;
	}

}
