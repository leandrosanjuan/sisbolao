package managedBean;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pojo.Perfil;
import pojo.Permissao;
import pojo.Usuario;
import util.CriaHash;
import util.MessagesReader;
import bo.IUsuarioBO;
import bo.implementation.UsuarioBO;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB {

	private Usuario usuario;
	private Usuario usuarioLogado;
	private List<Permissao> permissoes;
	private boolean isAdmin;
	private int parametro;	
	private String novaSenha;
	private String confirmaNovaSenha;
	private FacesContext ctx;
	

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
		usuario = new Usuario();
		usuarioLogado = null;
		return "index?faces-redirect=true";
	}
	
	public String preAlterar(){
		
		switch (parametro) {
		case 0:
			usuario = usuarioLogado;
			return "alteraremail?faces-redirect=true";
			
		case 1:
			usuario = new Usuario();
			return "alterarsenha?faces-redirect=true";			
		
		}
		return null;
	}
	public void alterarEmail(){
		usuarioLogado.setEmail(usuario.getEmail());
		usuarioBO.update(usuarioLogado);
	}
	public void alterarSenha(){
		ctx = FacesContext.getCurrentInstance();
		String msg;
		FacesMessage facesMsg;
		
		try {
			String senha = CriaHash.SHA1(usuario.getSenha());
			if(!senha.equals(usuarioLogado.getSenha())) {
				msg = MessagesReader.getMessages().getProperty("senhasIncorreta");
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg, msg);
				
			}else if(!novaSenha.equals(confirmaNovaSenha)) {
				msg = MessagesReader.getMessages().getProperty("senhasDiferentes");
				facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg, msg);
			} else {
				usuarioLogado.setSenha(senha);
				usuarioBO.update(usuarioLogado);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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



	public void setParametro(int parametro) {
		this.parametro = parametro;
	}



	public int getParametro() {
		return parametro;
	}



	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}



	public String getNovaSenha() {
		return novaSenha;
	}



	public void setConfirmaNovaSenha(String confirmaNovaSenha) {
		this.confirmaNovaSenha = confirmaNovaSenha;
	}



	public String getConfirmaNovaSenha() {
		return confirmaNovaSenha;
	}

}
