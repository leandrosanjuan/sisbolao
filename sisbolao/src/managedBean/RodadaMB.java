package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	
	public RodadaMB(){
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

}
