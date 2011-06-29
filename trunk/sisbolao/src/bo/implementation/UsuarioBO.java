package bo.implementation;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.Usuario;
import util.MessagesReader;
import dao.UsuarioDao;
import bo.IUsuarioBO;

public class UsuarioBO implements IUsuarioBO {
	FacesContext ctx;
	FacesMessage msg;
	UsuarioDao usuarioDao;
	Logger logger;

	public UsuarioBO() {
		usuarioDao = new UsuarioDao();
		ctx = FacesContext.getCurrentInstance();
		logger = LoggerFactory.getLogger("UsuarioBO");
	}

	@Override
	public void create(Usuario usuario) {
		try {
			usuarioDao.create(usuario);
		} catch (Exception e) {
			logger.error(e.getMessage());
			String mensagem = MessagesReader
			.getMessages().getProperty("erroPersistUpdate");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem ,mensagem);
			ctx.addMessage(null, msg);
		}

	}

	@Override
	public void update(Usuario usuario) {
		try {
			usuarioDao.update(usuario);
		} catch (Exception e) {
			logger.error(e.getMessage());
			String mensagem = MessagesReader
			.getMessages().getProperty("erroPersistUpdate");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem ,mensagem);
			ctx.addMessage(null, msg);
		}

	}

	@Override
	public Usuario login(Usuario usuario) {
		try{
			return usuarioDao.findByLoginSenha(usuario);
			 
		} catch(NoResultException e) {
			logger.error(e.getMessage());
			String mensagem = MessagesReader
			.getMessages().getProperty("loginSenhaInvalido");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem ,mensagem);
			ctx.addMessage(null, msg);
			
			return null;
		}
		

	}

	@Override
	public boolean logout(Usuario usuario) {
		return false;

	}
	@Override
	public boolean existeLogin(String login) {
		// TODO Auto-generated method stub
		return false;
	}

}
