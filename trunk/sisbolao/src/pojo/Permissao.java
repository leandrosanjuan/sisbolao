package pojo;

public enum Permissao {
	BOLAO(0),
	CAMPEONATO(1),
	TIME(2),
	RODADA(3),
	DADOSPESSOAIS(4),
	PALPITE(5);
	
	private int permissao;

	private Permissao(int permissao) {
		this.permissao = permissao;
	}
}
