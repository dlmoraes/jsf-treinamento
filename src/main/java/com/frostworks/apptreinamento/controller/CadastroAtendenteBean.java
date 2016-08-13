package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.cdi.ViewScoped;

import com.frostworks.apptreinamento.model.Agencia;
import com.frostworks.apptreinamento.model.Atendente;
import com.frostworks.apptreinamento.model.Cargo;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.repository.Agencias;
import com.frostworks.apptreinamento.repository.Cargos;
import com.frostworks.apptreinamento.service.CadastroAtendente;
import com.frostworks.apptreinamento.service.NegocioException;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("CadAtendenteMB")
@ViewScoped
public class CadastroAtendenteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroAtendente cadastro;
	@Inject
	private Agencias agencias;
	@Inject
	private Cargos cargos;
	private Atendente atendente;
	private List<Agencia> listaAgencia;
	private Boolean validarSituacao;
	private Boolean validarDataDesligamento;
	private Boolean checkMatricula;

	public CadastroAtendenteBean() {
		this.listaAgencia = new ArrayList<>();
	}

	public void inicializar() {
		if (atendente == null) {
			atendente = new Atendente();
			validarDataDesligamento = false;
			validarSituacao = false;
		} else {

			validarSituacao = true;
			validarDataDesligamento = false;
		}
		if (FacesUtil.isNotPostBack()) {
			carregarAgencias();
		}
		checkMatricula = !StringUtils.isBlank(this.atendente.getMatricula());
	}

	public void carregarAgencias() {
		this.listaAgencia = agencias.todas();
	}

	public void salvar() {
		try {
			cadastro.salvar(atendente);
			atendente = new Atendente();
			FacesUtil.addInfoMessage("Atendente salvo com sucesso!");
		} catch (NegocioException ex) {
			FacesUtil.addErrorMessage(ex.getMessage());
		}
	}

	public void informarDtaDesligamento() {
		System.out.println("Verificando situação...");
		if (atendente.getSituacao() == Situacao.INATIVO) {
			validarDataDesligamento = true;
			System.out.println("Data de desligamento habilitado.");
		} else {
			validarDataDesligamento = false;
			System.out.println("Data de desligamento desabilitado.");
		}
	}

	public List<Cargo> completarCargo(String cargo) {
		return this.cargos.filtrado(cargo);
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	public List<Agencia> getListaAgencia() {
		return listaAgencia;
	}

	public Situacao[] getSituacoes() {
		return Situacao.values();
	}

	public Boolean getValidarSituacao() {
		return validarSituacao;
	}

	public Boolean getValidarDataDesligamento() {
		return validarDataDesligamento;
	}

	public Boolean getCheckMatricula() {
		return checkMatricula;
	}

}
