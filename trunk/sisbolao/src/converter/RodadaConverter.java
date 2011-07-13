package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import pojo.Rodada;
import bo.IRodadaBO;
import bo.implementation.RodadaBO;

@FacesConverter(forClass = Rodada.class, value = "rodadaConverter")
public class RodadaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent arg1, String texto) {

		try {
			if (texto == null) {
				return null;
			}

			IRodadaBO rodadaBO = new RodadaBO();
			Rodada rodada = new Rodada();

			rodada.setId(Long.parseLong(texto));

			return rodadaBO.findById(rodada);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent arg1, Object obj) {

		try {
			if (obj == null || obj.toString().equals("")) {
				return "";
			} else {
				Rodada rodada = new Rodada();
				rodada.setId((Long) obj);
				
				IRodadaBO rodadaBO = new RodadaBO();
				Rodada umaRodada = rodadaBO.findById(rodada);
				
				return umaRodada.getNome();
			}

		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}

}
