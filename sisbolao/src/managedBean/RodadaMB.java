package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bo.IRodadaBO;
import bo.implementation.RodadaBO;

import pojo.Permissao;
import pojo.Rodada;
import pojo.Usuario;

@ManagedBean(name = "rodadaMB")
@SessionScoped
public class RodadaMB {

	private static Usuario usuario;

	private IRodadaBO rodadaBO;
	private Rodada rodada;
	
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

}
