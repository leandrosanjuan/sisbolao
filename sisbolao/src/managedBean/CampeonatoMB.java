package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pojo.Campeonato;
import pojo.Permissao;
import pojo.Usuario;
import bo.ICampeonatoBO;
import bo.implementation.CampeonatoBO;

@ManagedBean(name = "campeonatoMB")
@SessionScoped
public class CampeonatoMB {
	private static Usuario usuario;

	private ICampeonatoBO campeonatoBO;
	private Campeonato campeonato;

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
		campeonatoBO.create(campeonato);
	}

	public void alterarCampeonato() {
		campeonatoBO.update(campeonato);
	}

	public void excluirCampeonato() {
		campeonatoBO.delete(campeonato);
	}

	public List<Campeonato> listarCampeonatos() {
		return campeonatoBO.findAll();
	}
		
}
