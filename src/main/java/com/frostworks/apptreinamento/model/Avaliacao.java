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
import javax.persistence.Transient;

@Entity
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_av")
	private Long id;
	@Column(name = "nota1_av", scale = 2, precision = 2, nullable = false)
	private Double nota1 = 0.00;
	@Column(name = "nota2_av", scale = 2, precision = 2)
	private Double nota2 = 0.00;
	@Column(name = "arquivo_nota1_av", nullable = false, length = 50)
	private String arquivoNota1;
	@Column(name = "arquivo_nota2_av", length = 50)
	private String arquivoNota2;
	@Temporal(TemporalType.DATE)
	@Column(name = "dta_aplicacao_prova1_av", nullable = false)
	private Date dtaAplicacaoProva1;
	@Temporal(TemporalType.DATE)
	@Column(name = "dta_aplicacao_prova2_av")
	private Date dtaAplicacaoProva2;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = true, name = "dta_criacao_av")
	private Date dtaCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true, name = "dta_alteracao_av")
	private Date dtaModificacao;
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "id_lo_av")
	// private Lotacao lotacao;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ma_av")
	private Matricula matricula;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArquivoNota1() {
		return arquivoNota1;
	}

	public void setArquivoNota1(String arquivoNota1) {
		this.arquivoNota1 = arquivoNota1;
	}

	public String getArquivoNota2() {
		return arquivoNota2;
	}

	public void setArquivoNota2(String arquivoNota2) {
		this.arquivoNota2 = arquivoNota2;
	}

	public Double getNota1() {
		return nota1;
	}

	public void setNota1(Double nota1) {
		this.nota1 = nota1;
	}

	public Double getNota2() {
		return nota2;
	}

	public void setNota2(Double nota2) {
		this.nota2 = nota2;
	}

	public Date getDtaAplicacaoProva1() {
		return dtaAplicacaoProva1;
	}

	public void setDtaAplicacaoProva1(Date dtaAplicacaoProva1) {
		this.dtaAplicacaoProva1 = dtaAplicacaoProva1;
	}

	public Date getDtaAplicacaoProva2() {
		return dtaAplicacaoProva2;
	}

	public void setDtaAplicacaoProva2(Date dtaAplicacaoProva2) {
		this.dtaAplicacaoProva2 = dtaAplicacaoProva2;
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

	// public Lotacao getLotacao() {
	// return lotacao;
	// }
	//
	// public void setLotacao(Lotacao lotacao) {
	// this.lotacao = lotacao;
	// }

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
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
		if (!(obj instanceof Avaliacao))
			return false;
		Avaliacao other = (Avaliacao) obj;
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
	public boolean isRecuperacao() {
		return this.getNota1() < 8 && this.nota1 > 0.00;
	}

	@Transient
	public boolean isAprovado() {
		if (this.getNota1() >= 8) {
			return true;
		} else if (this.getNota2() >= 8) {
			return true;
		}

		return false;

	}

	@Transient
	public String getDiretorio() {
		if (System.getProperty("os.name").startsWith("Windows")) {
			return "\\imagem\\avaliacao\\";
		}
		return "/imagem/avaliacao/";
	}

}
