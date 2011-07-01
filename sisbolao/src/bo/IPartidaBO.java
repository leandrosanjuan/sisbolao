package bo;

import java.util.List;

import pojo.Partida;

public interface IPartidaBO {
	public void create(Partida partida);

	public void update(Partida partida);

	public void delete(Partida partida);

	public Partida findById(Partida partida);

	public List<Partida> findAll();
}
