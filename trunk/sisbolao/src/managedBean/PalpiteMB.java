package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pojo.Campeonato;
import pojo.Palpite;
import pojo.Partida;
import pojo.Permissao;
import pojo.Usuario;
import bo.ICampeonatoBO;
import bo.IPartidaBO;
import bo.implementation.CampeonatoBO;
import bo.implementation.PartidaBO;

@ManagedBean(name="palpiteMB")
@SessionScoped
public class PalpiteMB {

	private static Usuario usuario;
	
	private List<Palpite> palpites;
	private Campeonato campeonato;
	private List<Campeonato> campeonatos;
	private ICampeonatoBO campeonatoBO;
	private IPartidaBO partidaBO;
	private List<Partida> partidasProxRodada;

	
	public PalpiteMB() {
		// TODO Auto-generated constructor stub
	}
	

	public static boolean permissao(Usuario usuarioLogado) {
		usuario = usuarioLogado;
		if(usuario.getPermissoes().contains(Permissao.PALPITE)){
			return true;
		}
		return false;
	}
	
	public void proximaRodada() {
		palpites = new ArrayList<Palpite>();
		setPartidasProxRodada(new ArrayList<Partida>());
		if (campeonato != null) {
			partidaBO = new PartidaBO();
			partidasProxRodada = partidaBO.findProximaRodada(campeonato);
			for (Partida partida : partidasProxRodada) {
				Palpite palpite = new Palpite();
				palpite.setPartida(partida);
				palpites.add(palpite);
			}
		} 
		 
		
	}
	
	public void salvarPalpiteProxRodada(){
		for (Palpite palpite : palpites) {
			System.out.println(palpite.getGolCasa());
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


	public void setPartidasProxRodada(List<Partida> partidasProxRodada) {
		this.partidasProxRodada = partidasProxRodada;
	}

	public List<Partida> getPartidasProxRodada() {
		return partidasProxRodada;
	}

	public void setPalpites(List<Palpite> palpites) {
		this.palpites = palpites;
	}

	public List<Palpite> getPalpites() {
		return palpites;
	}

}
