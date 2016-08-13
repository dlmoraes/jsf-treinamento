package com.frostworks.apptreinamento.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.frostworks.apptreinamento.model.Avaliacao;
import com.frostworks.apptreinamento.model.Matricula;
import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.Avaliacoes;
import com.frostworks.apptreinamento.repository.Matriculas;
import com.frostworks.apptreinamento.repository.Treinamentos;
import com.frostworks.apptreinamento.service.NegocioException;
import com.frostworks.apptreinamento.service.UploadArquivo;

@Named
@ViewScoped
public class ListagemMatriculasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Matriculas matriculas;
	@Inject
	private Treinamentos treinamentos;
	@Inject
	private Avaliacoes avaliacoes;
	private Matricula selecionada;
	private Treinamento treinamentoSelecionado;
	private Long id_lot;
	private Long id_tre;
	private List<Matricula> lista;
	private StreamedContent prova;

	public ListagemMatriculasBean() {
		this.lista = new ArrayList<>();
		this.selecionada = new Matricula();
	}

	@PostConstruct
	public void inicializar() {
		if (this.id_lot != null) {
			System.out.println("Lotação informada! Nr: " + this.id_lot);
			this.lista = this.matriculas.porLotacao(this.id_lot);
		}
		if (this.id_tre != null) {
			System.out.println("Lotação informada! Nr: " + this.id_tre);
			this.treinamentoSelecionado = this.treinamentos.porId(this.id_tre);
		}
		if (this.lista.isEmpty()) {
			System.out.println("Nenhuma matricula carregada!");
		}
	}
	
	public StreamedContent getProvaAtendente() {
		Avaliacao avaliacao = this.avaliacoes.porMatricula(this.selecionada.getId());
		File logo = new File(UploadArquivo.getDirImagens() + avaliacao.getDiretorio() + avaliacao.getArquivoNota1());
		DefaultStreamedContent content = null;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(logo));
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "application/pdf");
		} catch (Exception e) {
			throw new NegocioException("Erro ao carregar o pdf.");
		}
		return content;
	}

	public Matricula getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Matricula selecionada) {
		this.selecionada = selecionada;
	}

	public List<Matricula> getLista() {
		return lista;
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

	public StreamedContent getProva() {
		return prova;
	}

	public void setProva(StreamedContent prova) {
		this.prova = prova;
	}

}
