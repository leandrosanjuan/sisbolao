package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bo.IBolaoBO;

import pojo.Campeonato;
import pojo.Permissao;
import pojo.Usuario;


@ManagedBean(name="bolaoMB")
@SessionScoped
public class BolaoMB {
	
	private static Usuario usuario;
	private IBolaoBO bolaoBO;
	
	
	private List<Campeonato> campeonatosDisponiveis;
	private Campeonato campeonato;
	
	public static boolean permissao(Usuario usuarioLogado){
		usuario = usuarioLogado;
		if(usuario.getPermissoes().contains(Permissao.BOLAO)){
			return true;
		}
		return false;
	}
	
	public void criarBolao(){
		
	}
	
	public List<Campeonato> getCampeonatosDisponiveis() {
		return campeonatosDisponiveis;
	}


	public void setCampeonatosDisponiveis(List<Campeonato> campeonatosDisponiveis) {
		this.campeonatosDisponiveis = campeonatosDisponiveis;
	}


	public Campeonato getCampeonato() {
		return campeonato;
	}


	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}


	

	
	
}
