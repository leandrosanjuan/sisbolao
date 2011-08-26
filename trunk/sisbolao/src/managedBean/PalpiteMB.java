package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pojo.Bolao;
import pojo.Campeonato;
import pojo.Palpite;
import pojo.Partida;
import pojo.Permissao;
import pojo.Rodada;
import pojo.Usuario;
import bo.IBolaoBO;
import bo.ICampeonatoBO;
import bo.IPartidaBO;
import bo.IRodadaBO;
import bo.implementation.BolaoBO;
import bo.implementation.CampeonatoBO;
import bo.implementation.PartidaBO;

@ManagedBean(name = "palpiteMB")
@SessionScoped
public class PalpiteMB implements Serializable {

	private static Usuario usuario;
	private FacesContext ctx;

	private List<Palpite> palpites;

	private Bolao bolao;
	private List<Bolao> boloes;
	private IBolaoBO bolaoBO;

	private Campeonato campeonato;
	private List<Campeonato> campeonatos;
	private ICampeonatoBO campeonatoBO;

	private IPartidaBO partidaBO;
	private List<Partida> partidasProxRodada;
	
	private IRodadaBO rodadaBO;
	private Rodada rodada;
	private long rodadaID;
	private List<Rodada> rodadas;

	public PalpiteMB() {

		bolaoBO = new BolaoBO();
		boloes = bolaoBO.findByParticipant(usuario);
		bolao = new Bolao();

		campeonatoBO = new CampeonatoBO();
		campeonatos = campeonatoBO.findAll();
		campeonato = new Campeonato();

	}

	public static boolean permissao(Usuario usuarioLogado) {
		usuario = usuarioLogado;
		if (usuario.getPermissoes().contains(Permissao.PALPITE)) {
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

	public void salvarPalpiteProxRodada() {
		for (Palpite palpite : palpites) {
			System.out.println(palpite.getGolCasa());
		}

	}
	
	public void filtrarRodadas() {

		if (campeonato == null) {
			this.setRodadas(new ArrayList<Rodada>());
		} else {
			setRodadas(rodadaBO.findByCampeonato(campeonato));
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

	public List<Rodada> getRodadas() {
		return rodadas;
	}

	public void setRodadas(List<Rodada> rodadas) {
		this.rodadas = rodadas;
	}

	public long getRodadaID() {
		return rodadaID;
	}

	public void setRodadaID(long rodadaID) {
		this.rodadaID = rodadaID;
	}

}
