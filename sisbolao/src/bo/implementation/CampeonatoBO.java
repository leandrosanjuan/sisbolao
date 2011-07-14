package bo.implementation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.Bolao;
import pojo.Campeonato;
import pojo.Rodada;
import pojo.Usuario;
import util.MessagesReader;
import bo.ICampeonatoBO;
import dao.BolaoDao;
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
	public void create(Campeonato campeonato, int numRodadas) {
		ctx = FacesContext.getCurrentInstance();
		String mensagem;
		try {
			if (!campeonatoDAO.existeCampeonato(campeonato.getNome())) {
				campeonatoDAO.create(campeonato);
				for (int i = 0; i < numRodadas; i++) {
					RodadaBO rodadaBO = new RodadaBO();
					Rodada rodada = new Rodada();
					String nomeRodada = MessagesReader.getMessages()
							.getProperty("nomeRodadas");
					rodada.setNome((i + 1) + nomeRodada);
					rodada.setCampeonato(campeonato);
					rodadaBO.create(rodada);
				}
				mensagem = MessagesReader.getMessages().getProperty(
						"campeonatoCadastroSucesso");
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,
						mensagem);
			} else {
				mensagem = MessagesReader.getMessages().getProperty(
						"campeonatoJaCadastrado");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem,
						mensagem);
			}

			ctx.addMessage(null, msg);
		} catch (Exception e) {
			logger.error(e.getMessage());
			mensagem = MessagesReader.getMessages().getProperty(
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
			if (!campeonatoDAO.existeCampeonato(campeonato.getNome())) {
				campeonatoDAO.update(campeonato);
				String mensagem = MessagesReader.getMessages().getProperty(
						"campeonatoAlteradoSucesso");
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,
						mensagem);
			} else {
				String mensagem = MessagesReader.getMessages().getProperty(
						"campeonatoJaCadastrado");
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem,
						mensagem);
			}
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
	public Campeonato findById(Campeonato campeonato) {
		return campeonatoDAO.findById(Campeonato.class, campeonato.getId());

	}

	@Override
	public List<Campeonato> findAll() {
		return (List<Campeonato>) campeonatoDAO.findAll(Campeonato.class);
	}

	public Campeonato findByName(String nome) {

		try {
			List<Campeonato> listaCampeonato = this.findAll();

			for (Campeonato campeonato : listaCampeonato) {
				if (campeonato.getNome().equals(nome)) {
					return campeonato;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public List<Campeonato> findByBoloesUsuario(Usuario usuario) {
		return campeonatoDAO.findByBoloesUsuario(usuario);	
	}

}
