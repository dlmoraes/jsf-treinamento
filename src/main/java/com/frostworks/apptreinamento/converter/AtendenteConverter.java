package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Atendente;
import com.frostworks.apptreinamento.repository.Atendentes;

@FacesConverter(forClass = Atendente.class)
public class AtendenteConverter implements Converter {

	@Inject
	private Atendentes atendentes;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Atendente retorno = null;

		if (value != null && !"".equals(value)) {
			Long id = new Long(value);
			retorno = atendentes.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Atendente modelo = (Atendente) value;
			return modelo.getId() == null ? null : modelo.getId().toString();
		}
		return null;
	}

}
