package pojo;


public enum Perfil {

	USUARIO(0), ADMINISTRADOR(1);

	private int perfil;

	private Perfil(int perfil) {
		this.perfil = perfil;
	}
	
}