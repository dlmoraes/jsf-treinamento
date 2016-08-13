package com.frostworks.apptreinamento.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Lotacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_lo")
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, insertable = true, name = "dta_criacao_lo")
	private Date dtaCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = true, name = "dta_alteracao_lo")
	private Date dtaModificacao;
	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_lot", length = 15, nullable = false)
	private SituacaoLotacao situacao = SituacaoLotacao.ABERTA;
	@OneToMany(mappedBy = "lotacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Matricula> matriculas = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tr_lo")
	private Treinamento treinamento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_re_lo")
	private Regional regional;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_em_lo")
	private Empresa empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SituacaoLotacao getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoLotacao situacao) {
		this.situacao = situacao;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Treinamento getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
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
		if (!(obj instanceof Lotacao))
			return false;
		Lotacao other = (Lotacao) obj;
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

	public void adicionarMatriculaVazia() {
		if (this.isAberta()) {
			Atendente atendente = new Atendente();

			Matricula aluno = new Matricula();
			aluno.setAtendente(atendente);
			aluno.setLotacao(this);

			this.getMatriculas().add(0, aluno);
		}
	}

	public void removerMatriculaVazia() {
		Matricula primeiraMatricula = this.getMatriculas().get(0);

		if (primeiraMatricula != null && primeiraMatricula.getAtendente().getId() == null) {
			this.getMatriculas().remove(0);
		}
	}

	public void removeMatricula(int linha, Matricula matricula) {
		// this.removerMatriculaVazia();
		this.getMatriculas().remove(linha);
		// this.adicionarMatriculaVazia();
	}

	@Transient
	public boolean isAberta() {
		return SituacaoLotacao.EMANDAMENTO.equals(this.getSituacao()) || SituacaoLotacao.ABERTA.equals(this.getSituacao());
	}
	
	@Transient
	public boolean isNovo() {
		return this.getId() == null;
	}

	@Transient
	public boolean isTreinamentoNaoAlteravel() {
		return !this.isAberta();
	}
	
	@Transient
	public boolean isEmAndamento() {
		return this.getSituacao().equals(SituacaoLotacao.EMANDAMENTO);
	}
	
	@Transient
	public boolean isCancelavel() {
		System.out.println("Em andamento: " + this.isEmAndamento());
		return this.isEmAndamento();
	}
	
}
