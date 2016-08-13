package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Lotacao;
import com.frostworks.apptreinamento.repository.Lotacoes;

@FacesConverter(forClass = Lotacao.class)
public class LotacaoConverter implements Converter {

	@Inject
	private Lotacoes repositorio;

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		Lotacao retorno = null;
		if (valor != null && !"".equals(valor)) {
			Long pk = new Long(valor);
			retorno = repositorio.porId(pk);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) {
		if (valor != null) {
			Lotacao lotacao = (Lotacao) valor;
			return lotacao.getId() == null ? null : lotacao.getId().toString();
		}
		return null;
	}

}
