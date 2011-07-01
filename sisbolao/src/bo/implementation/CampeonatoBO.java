package bo.implementation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.Bolao;
import pojo.Campeonato;
import util.MessagesReader;
import bo.ICampeonatoBO;
import dao.CampeonatoDao;

public class CampeonatoBO implements ICampeonatoBO {
	FacesContext ctx;
	FacesMessage msg;
	CampeonatoDao campeonatoDAO;
	Logger logger;

	public CampeonatoBO() {
		campeonatoDAO = new CampeonatoDao();
		logger = LoggerFactory.getLogger("CampeonatoBO");
	}

	@Override
	public void create(Campeonato campeonato) {
		ctx = FacesContext.getCurrentInstance();
		try {			
			campeonatoDAO.create(campeonato);
			String mensagem = MessagesReader.getMessages().getProperty(
					"campeonatoCadastroSucesso");
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
	public void update(Campeonato campeonato) {
		ctx = FacesContext.getCurrentInstance();
		try {			
			campeonatoDAO.update(campeonato);
			String mensagem = MessagesReader.getMessages().getProperty(
					"campeonatoAlteradoSucesso");
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
	public void delete(Campeonato campeonato) {
		ctx = FacesContext.getCurrentInstance();
		try {			
			campeonatoDAO.delete(campeonato);
			String mensagem = MessagesReader.getMessages().getProperty(
					"campeonatoExcluidoSucesso");
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
	public Bolao findById(Campeonato campeonato) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Campeonato> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
