package com.frostworks.apptreinamento.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.frostworks.apptreinamento.model.Atendente;
import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.Lotacao;
import com.frostworks.apptreinamento.model.Matricula;
import com.frostworks.apptreinamento.model.Regional;
import com.frostworks.apptreinamento.model.Situacao;
import com.frostworks.apptreinamento.model.TipoTreinamento;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.Atendentes;
import com.frostworks.apptreinamento.repository.Empresas;
import com.frostworks.apptreinamento.repository.Regionais;
import com.frostworks.apptreinamento.repository.Treinamentos;
import com.frostworks.apptreinamento.repository.filter.RealizarLotacaoFilter;
import com.frostworks.apptreinamento.service.GestaoLotacao;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("RealizarLotacaoMB")
@ViewScoped
public class RealizarLotacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestaoLotacao gestaoLotacao;
	@Inject
	private Treinamentos treinamentos;
	@Inject
	private Atendentes atendentes;
	@Inject
	private Empresas empresas;
	@Inject
	private Regionais regionais;

	private List<Treinamento> listaTreinamentos;
	private List<Empresa> listaEmpresas;
	private List<Regional> listaRegionais;

	private Lotacao lotacao;
	private Atendente atendenteLinhaEditavel;
	private RealizarLotacaoFilter filtro;
	private String matAtendente;
	private boolean habilitarMatriculas;

	public RealizarLotacaoBean() {
		listaEmpresas = new ArrayList<>();
		listaTreinamentos = new ArrayList<>();
		lotacao = new Lotacao();
		filtro = new RealizarLotacaoFilter();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			carregarEmpresas();
		}

		if (lotacao == null) {
			lotacao = new Lotacao();
		} else {
			carregarDados(lotacao.getTreinamento().getTipoTreinamento(), lotacao.getTreinamento().getEmpresa());
		}

		this.lotacao.adicionarMatriculaVazia();

	}

	public void salvar() {
		try {
			this.lotacao.removerMatriculaVazia();
			this.gestaoLotacao.salvar(this.lotacao);
			FacesUtil.addInfoMessage("Lotação efetuada com sucesso!");
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao salvar está lotação!");
		}
	}

	public void carregarEmpresas() {
		listaEmpresas = new ArrayList<Empresa>();
		listaEmpresas = empresas.todas();
	}

	public void carregarRegionais() {
		listaRegionais = new ArrayList<Regional>();
		listaRegionais = regionais.porEmpresa(lotacao.getEmpresa().getId());
	}

	public void carregarTreinamentos() {
		listaTreinamentos = new ArrayList<Treinamento>();
		try {
			listaTreinamentos = treinamentos.porTipoeEmpresa(filtro.getTipo(), lotacao.getEmpresa().getId());
			System.out.println("Size: " + listaTreinamentos.size());
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao carregar os treinamentos");
		}
	}

	public void carregarDados(TipoTreinamento tipo, Empresa empresa) {
		listaTreinamentos = new ArrayList<Treinamento>();
		try {
			listaTreinamentos = treinamentos.porTipoeEmpresa(tipo, empresa.getId());
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao carregar os treinamentos");
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

	public List<Atendente> completarAtendente(String nome) {

		try {
			return this.atendentes.porNomeAtivos(nome, lotacao.getEmpresa().getId(), filtro.getTipo(),
				lotacao.getRegional().getId());
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao carregar o atendente.");

		}
		return null;
	}

	public TipoTreinamento[] getTiposTreinamento() {
		return TipoTreinamento.values();
	}

	public List<Treinamento> getListaTreinamentos() {
		return listaTreinamentos;
	}

	public void onRowSelect(SelectEvent event) {
		FacesUtil.addInfoMessage("Treinamento: " + this.lotacao.getTreinamento().getTitulo() + " foi selecionado.");
		habilitarMatriculas = true;
		limpar();

		this.lotacao.adicionarMatriculaVazia();
	}

	public void onRowUnselect(UnselectEvent event) {
		habilitarMatriculas = false;
		limpar();
	}

	private void limpar() {
		lotacao = new Lotacao();
	}

	public RealizarLotacaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(RealizarLotacaoFilter filtro) {
		this.filtro = filtro;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public Lotacao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
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

	public boolean isHabilitarMatriculas() {
		return habilitarMatriculas;
	}

	public List<Regional> getListaRegionais() {
		return listaRegionais;
	}

}
