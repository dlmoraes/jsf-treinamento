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
public class Treinamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tr")
	private Long id;
	@Column(name = "titulo_tr", nullable = false, length = 50)
	private String titulo;
	@Temporal(TemporalType.DATE)
	@Column(name = "dta_inicio_tr", nullable = false)
	private Date dtaInicio;
	@Temporal(TemporalType.DATE)
	@Column(name = "dta_fim_tr", nullable = true)
	private Date dtaFim;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_treinamento_tr", length = 30, nullable = false)
	private TipoTreinamento tipoTreinamento;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = true, name = "dta_criacao_tr")
	private Date dtaCriacao = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true, name = "dta_alteracao_tr")
	private Date dtaModificacao = new Date();
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_tr", length = 15, nullable = false)
	private SituacaoTreinamento situacao;
	@Column(name = "objetivo_tr", columnDefinition = "text")
	private String objetivo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_emp_tr", nullable = false)
	private Empresa empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDtaInicio() {
		return dtaInicio;
	}

	public void setDtaInicio(Date dtaInicio) {
		this.dtaInicio = dtaInicio;
	}

	public Date getDtaFim() {
		return dtaFim;
	}

	public void setDtaFim(Date dtaFim) {
		this.dtaFim = dtaFim;
	}

	public TipoTreinamento getTipoTreinamento() {
		return tipoTreinamento;
	}

	public void setTipoTreinamento(TipoTreinamento tipoTreinamento) {
		this.tipoTreinamento = tipoTreinamento;
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

	public SituacaoTreinamento getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoTreinamento situacao) {
		this.situacao = situacao;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
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
		if (!(obj instanceof Treinamento))
			return false;
		Treinamento other = (Treinamento) obj;
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
	public boolean isAtivo() {
		return this.getSituacao().equals(SituacaoTreinamento.EMANDAMENTO);
	}

	@Transient
	public boolean isConcluido() {
		return this.getSituacao().equals(SituacaoTreinamento.CONCLUIDO);
	}

	@Transient
	public boolean isEncerravel() {
		return this.isAtivo();
	}

	@Transient
	public boolean isNaoEncerravel() {
		return !this.isEncerravel();
	}

	@Transient
	public boolean isNovo() {
		return this.getId() == null;
	}
	

}
