package bo;

import pojo.Usuario;

public interface IUsuarioBO {
	public void create(Usuario usuario);
	public void update(Usuario usuario);
	public Usuario login(Usuario usuario);
	public boolean logout(Usuario usuario);
	public boolean existeLogin(String login);
}
