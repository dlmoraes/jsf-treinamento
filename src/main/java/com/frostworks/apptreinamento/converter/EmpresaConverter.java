package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Empresa;
import com.frostworks.apptreinamento.repository.Empresas;

@FacesConverter(forClass=Empresa.class)
public class EmpresaConverter implements Converter {

	@Inject
	private Empresas empresas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Empresa retorno = null;
		
		if (value != null && !"".equals(value)) {
			Long id = new Long(value);
			retorno = empresas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Empresa modelo = (Empresa) value;
			return modelo.getId() == null ? null : modelo.getId().toString();
		}
		return null;
	}

}
