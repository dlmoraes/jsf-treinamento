package com.frostworks.apptreinamento.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_emp")
	private Long id;
	@Column(name = "nome_emp", length = 100, nullable = false)
	private String nome;
	@Column(name = "sigla_emp", length = 10, nullable = false, unique = true)
	private String sigla;
	@Column(name = "logo_emp", length = 50)
	private String logo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = true, name = "dta_criacao_emp")
	private Date dtaCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true, name = "dta_alteracao_emp")
	private Date dtaModificacao;

	public Long getId() {
		return id;
	}

	public void setId(Long idEmpresa) {
		this.id = idEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Date getDtaCriacao() {
		return dtaCriacao;
	}

	public void setDtaCriacao(Date dtaCriacao) {
		this.dtaCriacao = dtaCriacao;
	}

	public Date getDtaModificacao() {
		return dtaModificacao;
	}

	public void setDtaModificacao(Date dtaModificacao) {
		this.dtaModificacao = dtaModificacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Empresa))
			return false;
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	@Transient
	public String getDiretorio() {
		if (System.getProperty("os.name").startsWith("Windows")) {
			return "\\imagem\\empresa\\";
		}
		return "/imagem/empresa/";
	}
	
	

}
