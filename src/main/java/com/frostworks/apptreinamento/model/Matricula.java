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
public class Matricula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ma")
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = true, name = "dta_criacao_ma")
	private Date dtaCriacao = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true, name = "dta_alteracao_ma")
	private Date dtaModificacao = new Date();
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_ma", length = 15, nullable = false)
	private Situacao situacao = Situacao.ATIVO;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_at_ma")
	private Atendente atendente;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_em_ma")
	private Empresa empresa;
	@ManyToOne
	@JoinColumn(name = "id_lo_ma")
	private Lotacao lotacao;
	@Enumerated(EnumType.STRING)
	@Column(name = "resultado_ma", length = 15, nullable = false)
	private SituacaoAvaliacao resultado = SituacaoAvaliacao.NAOAVALIADO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
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


	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	
	public Lotacao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
	}
	
	public SituacaoAvaliacao getResultado() {
		return resultado;
	}

	public void setResultado(SituacaoAvaliacao resultado) {
		this.resultado = resultado;
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
		if (!(obj instanceof Matricula))
			return false;
		Matricula other = (Matricula) obj;
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
	public boolean isAtendenteAssociado() {
		return this.getAtendente() != null && this.getAtendente().getId() != null;
	}

}
