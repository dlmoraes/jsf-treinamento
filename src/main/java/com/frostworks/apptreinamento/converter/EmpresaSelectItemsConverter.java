package com.frostworks.apptreinamento.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;

import com.frostworks.apptreinamento.model.Empresa;

@FacesConverter("empresaSelectItemsConverter")
public class EmpresaSelectItemsConverter extends SelectItemsConverter {
	
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Long id = (value instanceof Empresa) ? ((Empresa) value).getId() : null;
        return (id != null) ? String.valueOf(id) : null;
    }
	
}
