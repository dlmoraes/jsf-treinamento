package com.frostworks.apptreinamento.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;

public class UploadArquivo implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String DIR_IMAGENS = "/home/diego/Plataforma/Gestao/Treinamentos/Arquivos";

	private String diretorio;
	private String caminho;
	private byte[] arquivo;
	private String nome;

	public UploadArquivo() {

	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getDiretorio() {
		return this.diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static String getDirImagens() {
		if (System.getProperty("os.name").startsWith("Windows")) {
			return "C:\\Plataforma\\Gestao\\Treinamento\\Arquivos";
		}
		return DIR_IMAGENS;
	}

	public String getRealPath() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		FacesContext aFacesContext = FacesContext.getCurrentInstance();
		ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

		if (System.getProperty("os.name").startsWith("Windows")) {
			return context.getRealPath("\\");
		} else {
			return context.getRealPath("/");
		}
	}

	public void fileUpload(FileUploadEvent event, String type, String diretorio) {
		try {
			this.nome = new java.util.Date().getTime() + type;
			this.caminho = getRealPath() + diretorio + getNome();
			this.caminho = getDirImagens() + diretorio + getNome();
			this.arquivo = event.getFile().getContents();

			File file = new File(getDirImagens() + diretorio);
			file.mkdirs();

		} catch (Exception ex) {
			System.out.println("Erro no upload do arquivo" + ex);
		}
	}

	public void gravar() {

		try {

			FileOutputStream fos;
			fos = new FileOutputStream(this.caminho);
			fos.write(this.arquivo);
			fos.close();

		} catch (Exception ex) {
			Logger.getLogger(UploadArquivo.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
