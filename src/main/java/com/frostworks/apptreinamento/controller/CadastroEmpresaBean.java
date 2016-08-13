package com.frostworks.apptreinamento.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.service.CadastroEmpresa;
import com.frostworks.apptreinamento.service.NegocioException;
import com.frostworks.apptreinamento.service.UploadArquivo;
import com.frostworks.apptreinamento.util.jsf.FacesUtil;

@Named("CadEmpresaMB")
@SessionScoped
public class CadastroEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroEmpresa cadastroEmpresa;

	private UploadArquivo arquivo = new UploadArquivo();
	
	private Empresa empresa;
	private StreamedContent logoMarca;
	private Boolean validado;

	public void inicializar() {
		if (empresa == null) {
			empresa = new Empresa();
			validado = false;
		} else {
			logoMarca = getImagem();
			validado = true;
		}
	}

	public void validarSigla() {
		System.out.println("Validando sigla...");
		validado = cadastroEmpresa.validarSigla(empresa.getSigla());
		if (!validado) {
			FacesUtil.addErrorMessage("A sigla informada já existe! Favor, informe outra sigla.");
		} else {
			FacesUtil.addInfoMessage("A sigla informada é valida!");
		}
	}
	
	public void salvar() {
		try {
			if (validado) {
				empresa = cadastroEmpresa.salvar(empresa);
				if (empresa.getLogo() != null && !"".equals(empresa.getLogo()))
					arquivo.gravar();
				empresa = new Empresa();
				FacesUtil.addInfoMessage("Empresa cadastrada com sucesso!");
			} else {
				FacesUtil.addErrorMessage("A sigla informada já existe! Favor, informe outra sigla.");
			}
		} catch (NegocioException ex) {
			FacesUtil.addErrorMessage(ex.getMessage());
		}
	}

	public void uploadAction(FileUploadEvent evento) {
		this.arquivo.fileUpload(evento, ".jpg", empresa.getDiretorio());
		this.empresa.setLogo(this.arquivo.getNome());
	}

	public StreamedContent getImagem() {
		File logo = new File(UploadArquivo.getDirImagens() + empresa.getDiretorio() + empresa.getLogo());
		DefaultStreamedContent content = null;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(logo));
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "image/jpeg");
		} catch (Exception e) {
			throw new NegocioException("Erro ao carregar a imagem.");
		}
		return content;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public StreamedContent getLogoMarca() {
		return logoMarca;
	}

	public void setLogoMarca(StreamedContent logoMarca) {
		this.logoMarca = logoMarca;
	}

	public Boolean getValidado() {
		return validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}

}
