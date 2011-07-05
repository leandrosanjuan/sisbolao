package bo.implementation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.Time;
import util.MessagesReader;
import bo.ITimeBO;
import dao.TimeDao;

public class TimeBO implements ITimeBO{
	FacesContext ctx;
	FacesMessage msg;
	TimeDao timeDao;
	Logger logger;
	
	public TimeBO() {
		timeDao = new TimeDao();
		logger = LoggerFactory.getLogger("TimeBO");
	}
	@Override
	public void create(Time time) {
		ctx = FacesContext.getCurrentInstance();
		try {
			timeDao.create(time);
			String mensagem = MessagesReader.getMessages().getProperty(
					"timeCadastradoSucesso");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,
					mensagem);
			ctx.addMessage(null, msg);
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
	public void update(Time time) {
		ctx = FacesContext.getCurrentInstance();
		try {
			timeDao.update(time);
			String mensagem = MessagesReader.getMessages().getProperty(
					"timeAlteradoSucesso");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,
					mensagem);
			ctx.addMessage(null, msg);
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
	public void delete(Time time) {
		ctx = FacesContext.getCurrentInstance();
		try {
			timeDao.delete(time);
			String mensagem = MessagesReader.getMessages().getProperty(
					"timeExcluidoSucesso");
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,
					mensagem);
			ctx.addMessage(null, msg);
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
	public Time findById(Time time) {
		return timeDao.findById(Time.class, time.getId());
	}

	@Override
	public List<Time> findAll() {
		return (List<Time>) timeDao.findAll(Time.class);
	}

}
