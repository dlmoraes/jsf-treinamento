package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.frostworks.apptreinamento.model.Agencia;
import com.frostworks.apptreinamento.repository.Agencias;

@FacesConverter(forClass = Agencia.class)
public class AgenciaConverter implements Converter {

	@Inject
	private Agencias repositorio; 
	
	@Override
	public Object getAsObject(FacesContext contexto, UIComponent componente, String valor) {
		Agencia retorno = null;
		
		if (valor != null && !"".equals(valor)) {
			Long pk = new Long(valor);
			retorno = repositorio.porId(pk);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext contexto, UIComponent componente, Object valor) {
		if (valor != null) {
			Agencia agencia = (Agencia) valor;
			return agencia.getId() == null ? null : agencia.getId().toString();
		}
		return null;
	}

}
