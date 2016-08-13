package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Matricula;
import com.frostworks.apptreinamento.repository.Matriculas;

@FacesConverter(forClass=Matricula.class)
public class MatriculaConverter implements Converter {

	@Inject
	private Matriculas matriculas;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Matricula retorno = null;
		
		if (value != null && !"".equals(value)) {
			Long id = new Long(value);
			retorno = matriculas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Matricula modelo = (Matricula) value;
			return modelo.getId() == null ? null : modelo.getId().toString();
		}
		return null;
	}

}
