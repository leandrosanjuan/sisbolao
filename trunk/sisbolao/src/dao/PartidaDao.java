package dao;

import java.util.List;

import javax.persistence.Query;

import pojo.Partida;
import pojo.Rodada;

public class PartidaDao extends AbstractDao<Partida>{

	public List<Partida> findByRodada(Rodada rodada) {
		try {
			Query query = em
					.createQuery("FROM Partida p WHERE p.rodada.id = ?1 order by p.dataHora");
			query.setParameter(1, rodada.getId());
			List<Partida> listaPartida = query.getResultList();

			return listaPartida;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
