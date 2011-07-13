package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import bo.implementation.CampeonatoBO;

import dao.CampeonatoDao;

import pojo.Campeonato;

@FacesConverter(forClass = Campeonato.class, value = "campeonatoConverter")
public class CampeonatoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent arg1, String texto) {
		if (texto == null) {
			return null;
		}

		CampeonatoBO campeonatoBO = new CampeonatoBO();

		return campeonatoBO.findByName(texto);
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent arg1, Object obj) {

		try {
			if (obj == null || obj.toString().equals("")) {
				return "";
			} else {
				Campeonato campeonato = (Campeonato) obj;
				return campeonato.getNome();
			}

		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}

}
