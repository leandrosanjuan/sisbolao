package managedBean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import bo.IUsuarioBO;
import bo.implementation.UsuarioBO;
import pojo.Usuario;

@ManagedBean(name="cadastroMB")
@SessionScoped
public class CadastroMB {
	private Usuario usuario;
	
	IUsuarioBO usuarioBO;
	
	public CadastroMB() {
		setUsuario(new Usuario());
		usuarioBO = new UsuarioBO();
	}
	
	public void cadastrar(){
		usuarioBO.create(usuario);
		usuario = new Usuario();		
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
}
