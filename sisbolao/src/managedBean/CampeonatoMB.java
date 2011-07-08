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
import util.MessagesReader;
import bo.ICampeonatoBO;
import bo.IRodadaBO;
import bo.implementation.CampeonatoBO;
import bo.implementation.RodadaBO;

@ManagedBean(name = "campeonatoMB")
@SessionScoped
public class CampeonatoMB {
	private static Usuario usuario;

	private ICampeonatoBO campeonatoBO;
	private IRodadaBO rodadaBO;
	private Campeonato campeonato;
	
	
	private int numRodadas;

	public CampeonatoMB() {
		campeonato = new Campeonato();
		campeonatoBO = new CampeonatoBO();
		numRodadas = 1;
		
	}

	public static boolean permissao(Usuario usuarioLogado) {
		usuario = usuarioLogado;
		if (usuario.getPermissoes().contains(Permissao.CAMPEONATO)) {
			return true;
		}
		return false;
	}
	
	public String preIncluir(){
		campeonato = new Campeonato();
		return "criarcampeonato?faces-redirect=true";
	}
	public void criarCampeonato() {
		rodadaBO = new RodadaBO();
		campeonatoBO.create(campeonato);	
		for(int i =0;i<numRodadas;i++){
			Rodada rodada = new Rodada();
			String nomeRodada = MessagesReader.getMessages().getProperty("nomeRodadas");
			rodada.setNome((i+1)+nomeRodada);
			rodada.setCampeonato(campeonato);
			rodadaBO.create(rodada);
		}			
		
			
		campeonato = new Campeonato();
		numRodadas = 1;
	}

	public void alterar() {
		campeonatoBO.update(campeonato);
	}

	public void excluir() {
		campeonatoBO.delete(campeonato);
	}

	public List<SelectItem> getCampeonatosSI() {
		List<Campeonato> campeonatos = getCampeonatos();
		
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
	
	public List<Campeonato> getCampeonatos(){
		return campeonatoBO.findAll();
		
	}
		
}
