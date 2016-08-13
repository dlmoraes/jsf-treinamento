package com.frostworks.apptreinamento.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Regional implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reg")
	private Long id;
	@Column(name = "nome_reg", nullable = false, length = 25)
	private String nome;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = true, name = "dta_criacao_reg")
	private Date dtaCriacao = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true, name = "dta_alteracao_reg")
	private Date dtaModificacao = new Date();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_emp_reg", nullable = false)
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
		if (!(obj instanceof Regional))
			return false;
		Regional other = (Regional) obj;
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

}
