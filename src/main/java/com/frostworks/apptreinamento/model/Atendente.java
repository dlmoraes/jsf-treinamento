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

import com.frostworks.apptreinamento.model.Agencia;
import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.model.Situacao;

@Entity
public class Atendente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_at")
	private Long id;
	@Column(name = "matricula_at", length = 10)
	private String matricula;
	@Column(name = "nome_at", nullable = false, length = 100)
	private String nome;
	@Temporal(TemporalType.DATE)
	@Column(nullable = true, name = "dta_entrevista_at")
	private Date dtaEntrevista;
	@Temporal(TemporalType.DATE)
	@Column(nullable = true, name = "dta_contratacao_at")
	private Date dtaContratacao;
	@Temporal(TemporalType.DATE)
	@Column(nullable = true, name = "dta_desligamento_at")
	private Date dtaDesligamento;
	@Column(name = "avatar_at")
	private String avatar;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = true, name = "dta_criacao_at")
	private Date dtaCriacao = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true, name = "dta_alteracao_at")
	private Date dtaModificacao = new Date();
	@Enumerated(EnumType.STRING)
	@Column(name = "situ_at", nullable = false, length = 15)
	private Situacao situacao = Situacao.ATIVO;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ca_at", nullable = false)
	private Cargo cargo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ag_at", nullable = false)
	private Agencia agencia;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_emp_at", nullable = false)
	private Empresa empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtaEntrevista() {
		return dtaEntrevista;
	}

	public void setDtaEntrevista(Date dtaEntrevista) {
		this.dtaEntrevista = dtaEntrevista;
	}

	public Date getDtaContratacao() {
		return dtaContratacao;
	}

	public void setDtaContratacao(Date dtaContratacao) {
		this.dtaContratacao = dtaContratacao;
	}

	public Date getDtaDesligamento() {
		return dtaDesligamento;
	}

	public void setDtaDesligamento(Date dtaDesligamento) {
		this.dtaDesligamento = dtaDesligamento;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
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
		if (!(obj instanceof Atendente))
			return false;
		Atendente other = (Atendente) obj;
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
	public boolean isNaoAlteravel() {
		return !this.isAlteravel();
	}
	
	@Transient
	public boolean isAlteravel() {
		return this.getSituacao().equals(Situacao.ATIVO);
	}
	
	@Transient
	public String getDiretorio() {
		if (System.getProperty("os.name").startsWith("Windows")) {
			return "\\imagem\\atendente\\";
		}
		return "/imagem/atendente/";
	}

}
