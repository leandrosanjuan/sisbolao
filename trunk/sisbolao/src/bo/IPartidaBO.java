package bo;

import java.util.List;

import pojo.Partida;
import pojo.Rodada;

public interface IPartidaBO {
	public void create(Partida partida);

	public void update(Partida partida);

	public void delete(Partida partida);

	public Partida findById(Partida partida);

	public List<Partida> findAll();

	public List<Partida> findByRodada(Rodada rodada);
}
