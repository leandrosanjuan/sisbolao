package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bo.ICampeonatoBO;
import bo.implementation.CampeonatoBO;

import pojo.Campeonato;
import pojo.Permissao;
import pojo.Usuario;

@ManagedBean(name="campeonatoMB")
@SessionScoped
public class CampeonatoMB {
	private static Usuario usuario;
	
	private ICampeonatoBO campeonatoBO;
	private Campeonato campeonato;
	
	
	public CampeonatoMB() {
		campeonato = new Campeonato();
		campeonatoBO = new CampeonatoBO();
	}
	public static boolean permissao(Usuario usuarioLogado){
		usuario = usuarioLogado;
		if(usuario.getPermissoes().contains(Permissao.CAMPEONATO)){
			return true;
		}
		return false;
	}
}
