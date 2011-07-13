package bo;

import java.util.List;

import pojo.Bolao;
import pojo.Campeonato;

public interface ICampeonatoBO {
	public void create(Campeonato campeonato,int numRodadas);

	public void update(Campeonato campeonato);

	public void delete(Campeonato campeonato);

	public Campeonato findById(Campeonato campeonato);

	public List<Campeonato> findAll();

	public Campeonato findByName(String label);
}
