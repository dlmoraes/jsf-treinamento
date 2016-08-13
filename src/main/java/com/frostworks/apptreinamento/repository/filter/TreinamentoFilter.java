package com.frostworks.apptreinamento.repository.filter;

import java.io.Serializable;
import java.util.Date;

import com.frostworks.apptreinamento.model.SituacaoTreinamento;
import com.frostworks.apptreinamento.model.TipoTreinamento;

public class TreinamentoFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private Date deInicio;
	private Date deFim;
	private TipoTreinamento[] tipos;
	private SituacaoTreinamento[] situacoes;
	private Long[] empresas;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDeInicio() {
		return deInicio;
	}

	public void setDeInicio(Date deInicio) {
		this.deInicio = deInicio;
	}

	public Date getDeFim() {
		return deFim;
	}

	public void setDeFim(Date deFim) {
		this.deFim = deFim;
	}

	public TipoTreinamento[] getTipos() {
		return tipos;
	}

	public void setTipos(TipoTreinamento[] tipos) {
		this.tipos = tipos;
	}

	public SituacaoTreinamento[] getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(SituacaoTreinamento[] situacoes) {
		this.situacoes = situacoes;
	}

	public Long[] getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Long[] empresas) {
		this.empresas = empresas;
	}

}
