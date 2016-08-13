package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Avaliacao;
import com.frostworks.apptreinamento.repository.Avaliacoes;

@FacesConverter(forClass = Avaliacao.class)
public class AvaliacaoConverter implements Converter {

	@Inject
	private Avaliacoes avaliacoes;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Avaliacao retorno = null;

		if (value != null && !"".equals(value)) {
			Long id = new Long(value);
			retorno = avaliacoes.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Avaliacao modelo = (Avaliacao) value;
			return modelo.getId() == null ? null : modelo.getId().toString();
		}
		return null;
	}

}
