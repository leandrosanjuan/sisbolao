package dao;

import java.util.List;

import javax.persistence.Query;

import pojo.Campeonato;
import pojo.Rodada;

public class RodadaDao extends AbstractDao<Rodada> {

	public List<Rodada> findByCampeonato(Campeonato campeonato) {
		Query query = em.createQuery("FROM Rodada r WHERE r.campeonato.id = ?1 order by r.id" );
		query.setParameter(1, campeonato.getId());
		List<Rodada> listaRodada = query.getResultList();

		return listaRodada;
	}

}
