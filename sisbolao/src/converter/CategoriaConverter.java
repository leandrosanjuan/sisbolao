package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pojo.Campeonato;
import pojo.Categoria;
import bo.implementation.CategoriaBO;

@FacesConverter(forClass = Categoria.class, value = "categoriaConverter")
public class CategoriaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent arg1, String texto) {
		if (texto == null) {
			return null;
		}

		CategoriaBO categoriaBO = new CategoriaBO();

		return categoriaBO.findByName(texto);
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent arg1, Object obj) {

		try {
			if (obj == null || obj.toString().equals("")) {
				return "";
			} else {
				Categoria categoria = (Categoria) obj;
				return categoria.getNome();
			}

		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}

}
