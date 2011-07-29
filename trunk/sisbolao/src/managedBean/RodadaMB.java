package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.DragDropEvent;

import pojo.Campeonato;
import pojo.Categoria;
import pojo.Partida;
import pojo.Permissao;
import pojo.Rodada;
import pojo.Time;
import pojo.Usuario;
import util.MessagesReader;
import bo.ICampeonatoBO;
import bo.ICategoriaBO;
import bo.IPartidaBO;
import bo.IRodadaBO;
import bo.ITimeBO;
import bo.implementation.CampeonatoBO;
import bo.implementation.CategoriaBO;
import bo.implementation.PartidaBO;
import bo.implementation.RodadaBO;
import bo.implementation.TimeBO;

@ManagedBean(name = "rodadaMB")
@SessionScoped
public class RodadaMB implements Serializable {

	private static Usuario usuario;

	private FacesContext ctx;

	private IRodadaBO rodadaBO;
	private Rodada rodada;
	private long rodadaID;
	private List<Rodada> rodadas;

	private List<Campeonato> campeonatos;
	private ICampeonatoBO campeonatoBO;
	private Campeonato campeonato;
	
	
	private ICategoriaBO categoriaBO;
	private Categoria categoria;
	private List<Categoria> categorias;
	
	private ITimeBO timeBO;
	private List<Time> times;
	private List<Time> timesEscolhidos;

	private Date dataHora;

	private IPartidaBO partidaBO;
	private Partida partida;
	private List<Partida> partidas;

	public RodadaMB() {
		rodada = new Rodada();
		rodadaBO = new RodadaBO();

		categoriaBO = new CategoriaBO();
		categorias = categoriaBO.findAll();
		categoria = new Categoria();
		
		timeBO = new TimeBO();
		times = new ArrayList<Time>();
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

//	public void onDrop(DragDropEvent event) {
//		Time time = (Time) event.getData();
//
//		timesEscolhidos.add(time);
//
//		FacesContext.getCurrentInstance().addMessage(null,
//				new FacesMessage(time.getNome() + " adicionado"));
//	}
//
//	public void onDropBack(DragDropEvent event) {
//		Time time = (Time) event.getData();
//
//		timesEscolhidos.remove(time);
//	}

	public void criarPartida() {

		rodada.setId(rodadaID);
		rodada = rodadaBO.findById(rodada);

		partida.setRodada(rodada);

		partidas.add(partida);
		partida = new Partida();
	}

	public void gravarPartidas() {
		for (Partida partida : partidas) {
			partidaBO.create(partida);
		}
	}

	public void gravarRodada() {
		ctx = FacesContext.getCurrentInstance();

		gravarPartidas();
		rodada.setPartidas(partidas);

		try {
			rodadaBO.update(rodada);

			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(rodada.getNome() + " do "
									+ rodada.getCampeonato().getNome()
									+ " adicionada"));

			rodada = new Rodada();
			partidas.clear();

		} catch (Exception e) {
			String m = MessagesReader.getMessages().getProperty(
					"erroPersistUpdate");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, m,
					m);
			e.printStackTrace();
		}
	}

	public void filtrarRodadas() {

		if (campeonato == null) {
			this.setRodadas(new ArrayList<Rodada>());
		} else {
			setRodadas(rodadaBO.findByCampeonato(campeonato));
		}

	}

	public void filtrarPartidas() {
		
		rodada.setId(rodadaID);
		rodada = rodadaBO.findById(rodada);

		if (campeonato == null || rodada == null) {
			this.setPartidas(new ArrayList<Partida>());
		} else {
			setPartidas(partidaBO.findByRodada(rodada));
		}

	}
	
//	public void filtrarTimesPorCategoria() {
//
//		if (categoria == null) {
//			setTimes(new ArrayList<Time>());
//		} else {
//			setTimes(timeBO.findByCategoria(categoria));
//		}
//
//	}

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

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public List<Time> getTimes() {
		if (categoria == null) {
			setTimes(new ArrayList<Time>());
		} else {
			setTimes(timeBO.findByCategoria(categoria));
		}		
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

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
