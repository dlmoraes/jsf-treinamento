package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Treinamento;
import com.frostworks.apptreinamento.repository.Treinamentos;

@FacesConverter(forClass = Treinamento.class)
public class TreinamentoConverter implements Converter {

	@Inject
	private Treinamentos treinamentos;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Treinamento retorno = null;
		
		if (value != null && !"".equals(value)) {
			Long id = new Long(value);
			retorno = treinamentos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Treinamento modelo = (Treinamento) value;
			return modelo.getId() == null ? null : modelo.getId().toString();
		}
		return null;
	}

}

