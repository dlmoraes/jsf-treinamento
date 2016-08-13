package com.frostworks.apptreinamento.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Agencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ag")
	private Long id;
	@Column(name = "nome_ag", nullable = false, length = 100)
	private String nome;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = true, name = "dta_criacao_ag")
	private Date dtaCriacao = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true, name = "dta_alteracao_ag")
	private Date dtaModificacao = new Date();
	@Enumerated(EnumType.STRING)
	@Column(name = "situ_ag", nullable = false, length = 15)
	private Situacao situacao = Situacao.ATIVO;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_reg_ag", nullable = false)
	private Regional regional;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_emp_ag", nullable = false)
	private Empresa empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
		if (!(obj instanceof Agencia))
			return false;
		Agencia other = (Agencia) obj;
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
	public boolean isNovo() {
		return this.getId() == null;
	}

	@Transient
	public Empresa getIdEmpresaRegional() {
		return this.getRegional() != null ? this.getRegional().getEmpresa() : null;
	}

}
