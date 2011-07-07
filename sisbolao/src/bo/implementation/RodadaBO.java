package bo.implementation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;

import dao.CampeonatoDao;
import dao.RodadaDao;

import pojo.Campeonato;
import pojo.Rodada;
import bo.IRodadaBO;

public class RodadaBO implements IRodadaBO {

	FacesContext ctx;
	FacesMessage msg;
	RodadaDao rodadaDao;
	Logger logger;
	
	
	public RodadaBO() {
		rodadaDao = new RodadaDao();
	}
	@Override
	public void create(Rodada rodada) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Rodada rodada) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Rodada> findAll() {
		return (List<Rodada>) rodadaDao.findAll(Rodada.class);
	}

	@Override
	public Rodada findById(Rodada rodada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Rodada rodada) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Rodada> findByCampeonato(Campeonato campeonato) {		
		return (List<Rodada>) rodadaDao.findAll(Rodada.class);
	}

}
