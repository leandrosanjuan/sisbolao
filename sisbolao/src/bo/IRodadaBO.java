package bo;

import java.util.List;

import pojo.Campeonato;
import pojo.Rodada;

public interface IRodadaBO {
	public void create(Rodada rodada);

	public void update(Rodada rodada);

	public void delete(Rodada rodada);

	public Rodada findById(Rodada rodada);

	public List<Rodada> findAll();
	
	public List<Rodada> findByCampeonato(Campeonato campeonato);

}
