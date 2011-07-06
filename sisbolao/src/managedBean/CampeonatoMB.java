package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import pojo.Campeonato;
import pojo.Permissao;
import pojo.Rodada;
import pojo.Usuario;
import bo.ICampeonatoBO;
import bo.implementation.CampeonatoBO;

@ManagedBean(name = "campeonatoMB")
@SessionScoped
public class CampeonatoMB {
	private static Usuario usuario;

	private ICampeonatoBO campeonatoBO;
	private Campeonato campeonato;
	
	private int numRodadas;

	public CampeonatoMB() {
		campeonato = new Campeonato();
		campeonatoBO = new CampeonatoBO();
	}

	public static boolean permissao(Usuario usuarioLogado) {
		usuario = usuarioLogado;
		if (usuario.getPermissoes().contains(Permissao.CAMPEONATO)) {
			return true;
		}
		return false;
	}

	public void criarCampeonato() {
		Rodada rodada = new Rodada();		
		rodada.setCampeonato(campeonato);		
		campeonatoBO.create(campeonato);		
		campeonato = new Campeonato();
	}

	public void alterarCampeonato() {
		campeonatoBO.update(campeonato);
	}

	public void excluirCampeonato() {
		campeonatoBO.delete(campeonato);
	}

	public List<SelectItem> getCampeonatos() {
		
		List<Campeonato> campeonatos = campeonatoBO.findAll();
		List<SelectItem> campeonatosSI = new ArrayList<SelectItem>();
		for (Campeonato campeonato : campeonatos) {
			SelectItem si = new SelectItem(campeonato,campeonato.getNome());
			campeonatosSI.add(si);
		}
		return campeonatosSI;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public int getNumRodadas() {
		return numRodadas;
	}

	public void setNumRodadas(int numRodadas) {
		this.numRodadas = numRodadas;
	}
		
}
