package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pojo.Campeonato;
import pojo.Palpite;
import pojo.Partida;
import bo.ICampeonatoBO;
import bo.IPartidaBO;
import bo.implementation.CampeonatoBO;
import bo.implementation.PartidaBO;

@ManagedBean(name="palpiteMB")
@SessionScoped
public class PalpiteMB {

	private Palpite palpite;
	private Campeonato campeonato;
	private List<Campeonato> campeonatos;
	private ICampeonatoBO campeonatoBO;
	private IPartidaBO partidaBO;
	private List<Partida> partidasProxRodada;

	
	public PalpiteMB() {
		// TODO Auto-generated constructor stub
	}
	
	public void proximaRodada() {
		setPartidasProxRodada(new ArrayList<Partida>());
		if (campeonato != null) {
			partidaBO = new PartidaBO();
			partidasProxRodada = partidaBO.findProximaRodada(campeonato);
		} 
		 
		
	}
	
	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}
	public Campeonato getCampeonato() {
		return campeonato;
	}
	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}
	public List<Campeonato> getCampeonatos() {
		campeonatoBO = new CampeonatoBO();
		return campeonatoBO.findByBoloesUsuario(LoginMB.getUsuarioLogadoExt());
	}

	public void setPalpite(Palpite palpite) {
		this.palpite = palpite;
	}

	public Palpite getPalpite() {
		return palpite;
	}

	public void setPartidasProxRodada(List<Partida> partidasProxRodada) {
		this.partidasProxRodada = partidasProxRodada;
	}

	public List<Partida> getPartidasProxRodada() {
		return partidasProxRodada;
	}
}
