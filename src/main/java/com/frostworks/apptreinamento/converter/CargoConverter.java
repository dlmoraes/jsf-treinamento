package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Cargo;
import com.frostworks.apptreinamento.repository.Cargos;

@FacesConverter(forClass = Cargo.class)
public class CargoConverter implements Converter {

	@Inject
	private Cargos cargos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cargo retorno = null;

		if (value != null && !"".equals(value)) {
			Long id = new Long(value);
			retorno = cargos.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cargo modelo = (Cargo) value;
			return modelo.getId() == null ? null : modelo.getId().toString();
		}
		return null;
	}

}
