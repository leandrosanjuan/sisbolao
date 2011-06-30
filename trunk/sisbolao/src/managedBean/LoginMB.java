package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bo.IUsuarioBO;
import bo.implementation.UsuarioBO;

import pojo.Usuario;
import util.CriaHash;

@ManagedBean(name="loginMB")
@SessionScoped
public class LoginMB {
	
	private Usuario usuario;
	private Usuario usuarioLogado;
	private List<String> permissoes;
	
	public IUsuarioBO usuarioBO;
	public LoginMB() {
		usuario = new Usuario();
		usuarioBO = new UsuarioBO();
	}
	
	
	public String entrar(){
		
		usuarioLogado = usuarioBO.login(usuario);
		usuario = new Usuario();
		if(usuarioLogado != null){			
			return "principal?faces-redirect=true";
		} else {		
			
			return null;
		}
		
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


	public List<String> getPermissoes() {
		
		return permissoes;
	}

	
	
	
}
