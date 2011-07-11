package bo.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.Campeonato;
import pojo.Rodada;
import util.MessagesReader;
import bo.IRodadaBO;
import dao.RodadaDao;

public class RodadaBO implements IRodadaBO {

	FacesContext ctx;
	FacesMessage msg;
	RodadaDao rodadaDao;
	Logger logger;

	public RodadaBO() {
		rodadaDao = new RodadaDao();
		logger = LoggerFactory.getLogger("RodadaBO");
	}

	@Override
	public void create(Rodada rodada) {
		ctx = FacesContext.getCurrentInstance();
		try {
			rodadaDao.create(rodada);
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
	public void delete(Rodada rodada) {
		ctx = FacesContext.getCurrentInstance();
		try {
			rodadaDao.delete(rodada);
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
	public List<Rodada> findAll() {
		ctx = FacesContext.getCurrentInstance();
		try {
			return (List<Rodada>) rodadaDao.findAll(Rodada.class);
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
	public Rodada findById(Rodada rodada) {
		ctx = FacesContext.getCurrentInstance();
		try {
			return rodadaDao.findById(Rodada.class, rodada.getId());
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
	public void update(Rodada rodada) {
		ctx = FacesContext.getCurrentInstance();
		try {
			rodadaDao.update(rodada);
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
	public List<Rodada> findByCampeonato(Campeonato campeonato) {
		List<Rodada> listaRodada = new ArrayList<Rodada>();
		try {
			if (campeonato != null) {
				listaRodada = rodadaDao.findByCampeonato(campeonato);
				return listaRodada;
			} else {
				return listaRodada;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
