package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pojo.Perfil;
import pojo.Permissao;
import pojo.Usuario;
import bo.IUsuarioBO;
import bo.implementation.UsuarioBO;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB {

	private Usuario usuario;
	private Usuario usuarioLogado;
	private List<Permissao> permissoes;
	private boolean isAdmin;

	public IUsuarioBO usuarioBO;

	public LoginMB() {
		usuario = new Usuario();
		usuarioBO = new UsuarioBO();
		isAdmin = false;
	}

	public String entrar() {

		usuarioLogado = usuarioBO.login(usuario);
		usuario = new Usuario();
		if (usuarioLogado != null) {
			usuarioLogado.setPermissoes(getPermissoes());
			return "principal?faces-redirect=true";
		} else {

			return null;
		}

	}

	public String sair() {
		usuarioLogado = null;
		return "index?faces-redirect=true";
	}

	public String criarCampeonato() {
		if (CampeonatoMB.permissao(usuarioLogado)) {
			return "criarcampeonato?faces-redirect=true";
		}
		return null;
	}

	public String criarBolao() {
		if (BolaoMB.permissao(usuarioLogado)) {
			return "criarbolao?faces-redirect=true";
		}
		return null;
	}

	public String criarRodada() {
		if (RodadaMB.permissao(usuarioLogado)) {
			return "criarrodada?faces-redirect=true";
		}
		return null;
	}

	public String criarTime() {
		if (TimeMB.permissao(usuarioLogado)) {
			return "criartime?faces-redirect=true";
		}
		return null;
	}

	public String listarTimes() {
		return "listartimes?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public boolean getIsAdmin() {
		return (usuarioLogado.getPerfil() == Perfil.ADMINISTRADOR) ? true
				: false;
	}

	public List<Permissao> getPermissoes() {
		if (permissoes == null) {
			permissoes = new ArrayList<Permissao>();
			switch (usuarioLogado.getPerfil()) {
			case USUARIO:
				permissoes.add(Permissao.BOLAO);
				permissoes.add(Permissao.DADOSPESSOAIS);

				break;
			case ADMINISTRADOR:
				permissoes.add(Permissao.BOLAO);
				permissoes.add(Permissao.CAMPEONATO);
				permissoes.add(Permissao.DADOSPESSOAIS);
				permissoes.add(Permissao.RODADA);
				permissoes.add(Permissao.TIME);
				break;

			}
		}

		return permissoes;
	}

}
