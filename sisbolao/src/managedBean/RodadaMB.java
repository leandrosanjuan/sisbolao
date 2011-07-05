package managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;

import pojo.Permissao;
import pojo.Rodada;
import pojo.Time;
import pojo.Usuario;
import bo.IRodadaBO;
import bo.implementation.RodadaBO;

@ManagedBean(name = "rodadaMB")
@SessionScoped
public class RodadaMB {

	private static Usuario usuario;

	private IRodadaBO rodadaBO;
	private Rodada rodada;

	private Time timeCasa;
	private Time timeVisitante;

	private List<Time> times;

	private List<Time> timesEscolhidos;

	public RodadaMB() {
		rodada = new Rodada();
		rodadaBO = new RodadaBO();
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

}
