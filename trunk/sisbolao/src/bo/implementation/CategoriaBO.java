package bo.implementation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pojo.Categoria;
import util.MessagesReader;
import bo.ICategoriaBO;
import dao.CategoriaDao;
import dao.TimeDao;

public class CategoriaBO implements ICategoriaBO {
	FacesContext ctx;
	FacesMessage msg;
	CategoriaDao categoriaDao;
	Logger logger;
	
	public CategoriaBO() {
		categoriaDao = new CategoriaDao();
		logger = LoggerFactory.getLogger("CategoriaDao");
	}
	@Override
	public void create(Categoria categoria) {
		ctx = FacesContext.getCurrentInstance();
		try {
			if (!categoriaDao.existeCategoria(categoria.getNome())) {
				categoriaDao.create(categoria);
				String mensagem = MessagesReader.getMessages().getProperty(
						"categoriaCadastradaSucesso");
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,
						mensagem);

			} else {
				String mensagem = MessagesReader.getMessages().getProperty(
						"categoriaJaExiste");
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
	public void update(Categoria categoria) {
		ctx = FacesContext.getCurrentInstance();
		try {
			if (!categoriaDao.existeCategoria(categoria.getNome())) {
				categoriaDao.update(categoria);
				String mensagem = MessagesReader.getMessages().getProperty(
						"categoriaAlteradaSucesso");
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,
						mensagem);

			} else {
				String mensagem = MessagesReader.getMessages().getProperty(
						"categoriaJaExiste");
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
	public void delete(Categoria categoria) {
		ctx = FacesContext.getCurrentInstance();
		try {
			Categoria categoriaDefault = categoriaDao.findById(Categoria.class, 0);
			TimeDao timeDao = new TimeDao();
			timeDao.updateCategoriaDefault(categoria, categoriaDefault);
			categoriaDao.delete(categoria);
			String mensagem = MessagesReader.getMessages().getProperty(
					"categoriaExcluidaSucesso");
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
	public Categoria findById(Categoria categoria) {
		return categoriaDao.findById(Categoria.class, categoria.getId());
	}

	@Override
	public List<Categoria> findAll() {
		return (List<Categoria>) categoriaDao.findAll(Categoria.class);
	}
	
	public Categoria findByName(String nome){
		return categoriaDao.findByName(nome);
	}

}
