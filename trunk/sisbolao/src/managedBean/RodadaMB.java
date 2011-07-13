package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.DragDropEvent;

import pojo.Campeonato;
import pojo.Partida;
import pojo.Permissao;
import pojo.Rodada;
import pojo.Time;
import pojo.Usuario;
import bo.ICampeonatoBO;
import bo.IPartidaBO;
import bo.IRodadaBO;
import bo.ITimeBO;
import bo.implementation.CampeonatoBO;
import bo.implementation.PartidaBO;
import bo.implementation.RodadaBO;
import bo.implementation.TimeBO;

@ManagedBean(name = "rodadaMB")
@SessionScoped
public class RodadaMB implements Serializable {

	private static Usuario usuario;

	private IRodadaBO rodadaBO;
	private Rodada rodada;
	private long rodadaID;
	private List<Rodada> rodadas;

	private SelectItem rodadaSI;

	private List<Campeonato> campeonatos;
	private ICampeonatoBO campeonatoBO;
	private Campeonato campeonato;

	private ITimeBO timeBO;
	private List<Time> times;
	private List<Time> timesEscolhidos;

	private Time timeCasa;

	private Time timeVisitante;

	private Calendar dataHora;

	private IPartidaBO partidaBO;
	private Partida partida;
	private List<Partida> partidas;

	public RodadaMB() {
		rodada = new Rodada();
		rodadaBO = new RodadaBO();
		
		timeBO = new TimeBO();
		times = timeBO.findAll(); // Alterar depois!!!
		timesEscolhidos = new ArrayList<Time>();
		campeonatoBO = new CampeonatoBO();
		campeonatos = campeonatoBO.findAll();
		campeonato = new Campeonato();
		
		partidaBO = new PartidaBO();
		partida = new Partida();
		partidas = new ArrayList<Partida>();

	}

	public static boolean permissao(Usuario usuarioLogado) {
		usuario = usuarioLogado;
		if (usuario.getPermissoes().contains(Permissao.RODADA)) {
			return true;
		}
		return false;
	}

	public void onDrop(DragDropEvent event) {
		Time time = (Time) event.getData();

		timesEscolhidos.add(time);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(time.getNome() + " adicionado"));
	}

	public void onDropBack(DragDropEvent event) {
		Time time = (Time) event.getData();

		timesEscolhidos.remove(time);
	}

	public void criarPartida() {
		Partida partida = new Partida();
		partida.setTimeCasa(timeCasa);
		partida.setTimeVisitante(timeVisitante);
		partida.setDataHora(dataHora);

		rodada.setId(rodadaID);
		partida.setRodada(rodadaBO.findById(rodada));
		
		
		partidas.add(partida);
	}
	
	public void gravarPartidas(){
		for (Partida partida :partidas){
			partidaBO.update(partida);
		}
	}
	
	public void gravarRodada(){
		gravarPartidas();
		rodada.setPartidas(partidas);
		rodadaBO.update(rodada);
	}

	public void filtrarRodadas() {

		if (campeonato == null) {
			this.setRodadas(new ArrayList<Rodada>());
		} else {
			setRodadas(rodadaBO.findByCampeonato(campeonato));
		}

	}

	public void alterarRodada() {
		rodadaBO.update(rodada);
	}

	public void excluirRodada() {
		rodadaBO.delete(rodada);
	}

	public List<Rodada> listarRodadas() {
		return rodadaBO.findAll();
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public Time getTimeCasa() {
		return timeCasa;
	}

	public void setTimeCasa(Time timeCasa) {
		this.timeCasa = timeCasa;
	}

	public Time getTimeVisitante() {
		return timeVisitante;
	}

	public void setTimeVisitante(Time timeVisitante) {
		this.timeVisitante = timeVisitante;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public List<Time> getTimes() {
		return times;
	}

	public void setTimesEscolhidos(List<Time> timesEscolhidos) {
		this.timesEscolhidos = timesEscolhidos;
	}

	public List<Time> getTimesEscolhidos() {
		return timesEscolhidos;
	}

	public List<SelectItem> getRodadas() {
		List<Rodada> rodadas = rodadaBO.findByCampeonato(campeonato);
		List<SelectItem> rodadasSI = new ArrayList<SelectItem>();

		for (Rodada rodada : rodadas) {
			rodadasSI.add(new SelectItem(rodada.getId(), rodada.getNome()));
		}
		return rodadasSI;
	}

	public void setRodadas(List<Rodada> rodadas) {
		this.rodadas = rodadas;
	}

	// public List<Rodada> getRodadas() {
	// return rodadas;
	// }

	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(SelectItem siCampeonato) {
		this.campeonato = campeonatoBO.findByName(siCampeonato.getLabel());
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setRodadaID(long rodadaID) {
		this.rodadaID = rodadaID;
	}

	public long getRodadaID() {
		return rodadaID;
	}
}
