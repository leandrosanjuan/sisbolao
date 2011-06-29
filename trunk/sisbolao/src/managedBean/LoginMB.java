package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import bo.IUsuarioBO;

import pojo.Usuario;

@ManagedBean(name="login")
@SessionScoped
public class LoginMB {
	
	private Usuario usuario;
	
	public IUsuarioBO usuarioBO;
	
	
	public String entrar(){
		
		
		return "principal?faces-redirect=true";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
