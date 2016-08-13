package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Regional;
import com.frostworks.apptreinamento.repository.Regionais;

@FacesConverter(forClass = Regional.class)
public class RegionalConverter implements Converter {

	@Inject
	private Regionais repositorio;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Regional retorno = null;
		
		if (value != null && !"".equals(value)) {
			Long id = new Long(value);
			retorno = repositorio.getRegional(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Regional modelo = (Regional) value;
			return modelo.getId() == null ? null : modelo.getId().toString();
		}
		return null;
	}

}

