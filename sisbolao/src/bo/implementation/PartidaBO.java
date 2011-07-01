package bo.implementation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.PartidaDao;

import pojo.Partida;
import util.MessagesReader;
import bo.IPartidaBO;

public class PartidaBO implements IPartidaBO {
	
	FacesContext ctx;
	FacesMessage msg;
	PartidaDao partidaDao;
	Logger logger;
	
	public PartidaBO(){
		PartidaDao partidaDao = new PartidaDao();
		logger = LoggerFactory.getLogger("PartidaBO");
	}

	@Override
	public void create(Partida partida) {
		ctx = FacesContext.getCurrentInstance();

		try {
			partidaDao.create(partida);
		} catch (Exception e) {
			logger.error(e.getMessage());
			String mensagem = MessagesReader.getMessages().getProperty(
					"erroPersistUpdate");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
					mensagem);
			ctx.addMessage(null, msg);
		}

	}

	@Override
	public void delete(Partida partida) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Partida> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partida findById(Partida partida) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Partida partida) {
		// TODO Auto-generated method stub

	}

}
