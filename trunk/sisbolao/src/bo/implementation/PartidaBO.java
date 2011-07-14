package bo.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.PartidaDao;

import pojo.Bolao;
import pojo.Partida;
import util.MessagesReader;
import bo.IPartidaBO;

public class PartidaBO implements IPartidaBO {
	
	FacesContext ctx;
	FacesMessage msg;
	PartidaDao partidaDao;
	Logger logger;
	
	public PartidaBO(){
		partidaDao = new PartidaDao();
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
		ctx = FacesContext.getCurrentInstance();

		try {
			partidaDao.delete(partida);
		} catch (Exception e) {
			logger.error(e.getMessage());
			String mensagem = MessagesReader.getMessages().getProperty(
					"erroDelete");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
					mensagem);
			ctx.addMessage(null, msg);
		}

	}

	@Override
	public List<Partida> findAll() {
		ctx = FacesContext.getCurrentInstance();

		try {
			ArrayList<Partida> listaPartida = (ArrayList<Partida>) partidaDao
					.findAll(Partida.class);
			return listaPartida;
		} catch (Exception e) {
			logger.error(e.getMessage());
			String mensagem = MessagesReader.getMessages().getProperty("erroFind");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
					mensagem);
			ctx.addMessage(null, msg);
		}
		return null;
	}

	@Override
	public Partida findById(Partida partida) {
		ctx = FacesContext.getCurrentInstance();

		try {
			Partida umaPartida = partidaDao.findById(Partida.class, partida.getId());
			return umaPartida;
		} catch (Exception e) {
			logger.error(e.getMessage());
			String mensagem = MessagesReader.getMessages().getProperty(
					"erroFind");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
					mensagem);
			ctx.addMessage(null, msg);
		}
		return null;
	}

	@Override
	public void update(Partida partida) {
		ctx = FacesContext.getCurrentInstance();

		try {
			partidaDao.update(partida);
		} catch (Exception e) {
			logger.error(e.getMessage());
			String mensagem = MessagesReader.getMessages().getProperty(
					"erroPersistUpdate");
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem,
					mensagem);
			ctx.addMessage(null, msg);
		}

	}

}
