package bo.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.Bolao;
import pojo.Usuario;
import util.MessagesReader;
import bo.IBolaoBO;
import dao.BolaoDao;

public class BolaoBO implements IBolaoBO {

	FacesContext ctx;
	FacesMessage msg;
	BolaoDao bolaoDAO;
	Logger logger;

	public BolaoBO() {
		bolaoDAO = new BolaoDao();
		logger = LoggerFactory.getLogger("BolaoBO");
	}

	@Override
	public void create(Bolao bolao) {
		ctx = FacesContext.getCurrentInstance();
		
		try {
			bolaoDAO.create(bolao);
			String mensagem = MessagesReader.getMessages().getProperty(
			"bolaoCadastradoSucesso");
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
	public void delete(Bolao bolao) {
		ctx = FacesContext.getCurrentInstance();

		try {
			bolaoDAO.delete(bolao);
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
	public Bolao findById(Bolao bolao) {
		ctx = FacesContext.getCurrentInstance();

		try {
			Bolao umBolao = bolaoDAO.findById(Bolao.class, bolao.getId());
			return umBolao;
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
	public List<Bolao> findAll() {
		ctx = FacesContext.getCurrentInstance();

		try {
			ArrayList<Bolao> listaBolao = (ArrayList<Bolao>) bolaoDAO
					.findAll(Bolao.class);
			return listaBolao;
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
	public void update(Bolao bolao) {
		ctx = FacesContext.getCurrentInstance();

		try {
			bolaoDAO.update(bolao);
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
	public List<Bolao> findByParticipant(Usuario participante) {
		return bolaoDAO.findByParticipant(participante); 
	}

}
