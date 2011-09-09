package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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
	private static List<Bolao> boloes;
	private static IBolaoBO bolaoBO;
		
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
		setBolao(new Bolao());

		campeonatoBO = new CampeonatoBO();
		campeonatos = campeonatoBO.findAll();
		campeonato = new Campeonato();

	}

	public static boolean permissao(Usuario usuarioLogado) {
		usuario = usuarioLogado;
		if (usuario.getPermissoes().contains(Permissao.PALPITE)) {
			carregarBoloes();
			return true;
		}
		return false;
	}
	
	public static void carregarBoloes(){
		setBoloes(bolaoBO.findByParticipant(usuario));
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

		if (bolao == null) {
			this.setRodadas(new ArrayList<Rodada>());
		} else {
			setRodadas(rodadaBO.findByCampeonato(bolao.getCampeonato()));
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

	public List<SelectItem> getRodadas() {
		List<SelectItem> rodadasSI = new ArrayList<SelectItem>();
		if (bolao!=null){
			List<Rodada> rodadas = rodadaBO.findByCampeonato(bolao.getCampeonato());
			rodadasSI = new ArrayList<SelectItem>();

			for (Rodada rodada : rodadas) {
				rodadasSI.add(new SelectItem(rodada.getId(), rodada.getNome()));
			}
		}		
		return rodadasSI;
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

	public  List<Bolao> getBoloes() {
		return boloes;
	}

	public static void setBoloes(List<Bolao> boloes) {
		PalpiteMB.boloes = boloes;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

}
