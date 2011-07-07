package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import util.MessagesReader;
import bo.IUsuarioBO;
import bo.implementation.UsuarioBO;

@FacesValidator(value="validaUsuarioExistente")
public class UsuarioExistenteValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component, Object object)
			throws ValidatorException {
		String login = (String) object;
		
		IUsuarioBO usuarioBO = new UsuarioBO();
		
		if(usuarioBO.existeLogin(login)) {
			FacesMessage message = new FacesMessage();
			message.setDetail(MessagesReader.getMessages().getProperty(
					"erroUsuarioExistente"));
			message.setSummary(MessagesReader.getMessages().getProperty(
					"erroUsuarioExistente"));
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		
		
		
	}

}
