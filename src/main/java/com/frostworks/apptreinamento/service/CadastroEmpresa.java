package com.frostworks.apptreinamento.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.repository.Empresas;
import com.frostworks.apptreinamento.service.NegocioException;
import com.frostworks.apptreinamento.util.jpa.Transacional;

public class CadastroEmpresa implements Serializable {

	private static final long serialVersionUID = -5135619527332497183L;

	@Inject
	private Empresas repositorio;

	@Inject
	private ExcluirArquivo excluirArquivo;

	@Transacional
	public Empresa salvar(Empresa empresa) throws NegocioException {
		empresa.setSigla(empresa.getSigla().toUpperCase());
		if (empresa.getId() == null) {
			empresa.setDtaCriacao(new Date());
		} else {
			empresa.setDtaModificacao(new Date());
		}

		empresa = repositorio.salvar(empresa);

		return empresa;

	}

	public Boolean validarSigla(String sigla) {
		Boolean retorno = false;
		if (sigla != null && !"".equals(sigla)) {
			retorno = repositorio.validarSigla(sigla);
		}
		return retorno;
	}

	@Transacional
	public Empresa excluir(Empresa empresa) throws NegocioException {
		try {
			empresa = repositorio.porId(empresa.getId());
			if (empresa != null) {
				repositorio.excluir(empresa);
				excluirArquivo
						.excluirArquivo(UploadArquivo.getDirImagens() + empresa.getDiretorio() + empresa.getLogo());
			}
			return empresa;
		} catch (Exception ex) {
			throw new NegocioException("Erro ao excluir a empresa.");
		}
	}

}
