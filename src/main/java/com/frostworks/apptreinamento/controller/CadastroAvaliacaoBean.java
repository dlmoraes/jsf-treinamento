package com.frostworks.apptreinamento.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import com.frostworks.apptreinamento.model.Avaliacao;
import com.frostworks.apptreinamento.model.Matricula;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.Treinamentos;
import com.frostworks.apptreinamento.service.GestaoAvaliacao;
import com.frostworks.apptreinamento.service.NegocioException;
import com.frostworks.apptreinamento.service.UploadArquivo;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("CadAvaliacaoMB")
@SessionScoped
public class CadastroAvaliacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GestaoAvaliacao gestaoAvaliacao;
	@Inject
	private Treinamentos treinamentos;
	private Avaliacao avaliacao;
	private Matricula matricula;
	private Treinamento treinamentoSelecionado;
	private Long id_lot;
	private Long id_tre;
	private UploadArquivo arquivo1 = new UploadArquivo();
	private UploadArquivo arquivo2 = new UploadArquivo();

	public void inicializar() {
		if (this.matricula != null && this.avaliacao == null) {
			this.avaliacao = new Avaliacao();
			this.avaliacao.setMatricula(this.matricula);
			if (this.id_tre != null) {
				this.treinamentoSelecionado = this.treinamentos.porId(this.id_tre);
			}
		} else if (this.avaliacao == null) {
			throw new NegocioException("Sem matricula vinculada!");
		}
	}

	public void uploadActionNota1(FileUploadEvent evento) {
		this.arquivo1.fileUpload(evento, ".pdf", this.avaliacao.getDiretorio());
		this.avaliacao.setArquivoNota1(this.arquivo1.getNome());
	}
	
	public void uploadActionNota2(FileUploadEvent evento) {
		this.arquivo2.fileUpload(evento, ".pdf", this.avaliacao.getDiretorio());
		this.avaliacao.setArquivoNota2(this.arquivo2.getNome());
	}
	
	public void salvar() {
		this.gestaoAvaliacao.salvar(this.avaliacao);
		if (this.avaliacao.getArquivoNota1() != null && this.avaliacao.getNota1() > 0.00) {
			this.arquivo1.gravar();
		}
		if (this.avaliacao.getArquivoNota2() != null && this.avaliacao.getNota2() > 0.00) {
			this.arquivo2.gravar();
		}
		FacesUtil.addInfoMessage("Avaliação salva com sucesso!");
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Long getId_lot() {
		return id_lot;
	}

	public void setId_lot(Long id_lot) {
		this.id_lot = id_lot;
	}

	public Long getId_tre() {
		return id_tre;
	}

	public void setId_tre(Long id_tre) {
		this.id_tre = id_tre;
	}

	public Treinamento getTreinamentoSelecionado() {
		return treinamentoSelecionado;
	}

}
