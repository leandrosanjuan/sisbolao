package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pojo.Campeonato;
import pojo.Time;
import bo.implementation.TimeBO;

@FacesConverter(forClass = Campeonato.class, value = "timeConverter")
public class TimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String texto) {
		if (texto == null) {
			return null;
		}

		TimeBO timeBO = new TimeBO();

		return timeBO.findByName(texto);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			Time time = (Time) obj;
			return time.getNome();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
