package bo;

import java.util.List;

import pojo.Bolao;
import pojo.Campeonato;

public interface ICampeonatoBO {
	public void create(Campeonato campeonato);
	public void update(Campeonato campeonato);
	public void delete(Campeonato campeonato);
	public Campeonato findById(Campeonato campeonato);
	public List<Campeonato> findAll();
}
