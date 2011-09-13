package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pojo.Bolao;
import pojo.Campeonato;
import bo.IBolaoBO;
import bo.implementation.BolaoBO;

@FacesConverter(forClass = Campeonato.class, value = "bolaoConverter")
public class BolaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String id) {
		try {
			if (id == null || id.equals("")) {
				return null;
			}

			IBolaoBO bolaoBO = new BolaoBO();

			Bolao bolao = new Bolao();
			bolao.setId(Long.parseLong(id));

			return bolaoBO.findById(bolao);
		} catch (NumberFormatException e) {			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		try {
			if (obj == null || obj.toString().equals("")|| !(obj instanceof Bolao)) {
				return "";
			} else {				
				Bolao bolao = (Bolao) obj;
				return bolao.getNome();
			}

		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}

}
