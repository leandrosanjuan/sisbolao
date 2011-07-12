package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pojo.Campeonato;
import pojo.Rodada;
import bo.IRodadaBO;
import bo.implementation.RodadaBO;

@FacesConverter(forClass = Rodada.class, value = "rodadaConverter")
public class RodadaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent arg1, String texto) {
		if (texto == null) {
			return null;
		}

		IRodadaBO rodadaBO = new RodadaBO();
		Rodada rodada = new Rodada();

		rodada.setId(Long.valueOf(texto));

		return rodadaBO.findById(rodada);
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent arg1, Object obj) {

		try {
			Campeonato campeonato = (Campeonato) obj;
			return campeonato.getNome();

		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}

}
