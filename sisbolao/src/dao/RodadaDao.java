package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Query;

import pojo.Campeonato;
import pojo.Rodada;

public class RodadaDao extends AbstractDao<Rodada> {

	public List<Rodada> findByCampeonato(Campeonato campeonato) {
		try {
			Query query = em
					.createQuery("FROM Rodada r WHERE r.campeonato.id = ?1 order by r.id");
			query.setParameter(1, campeonato.getId());
			List<Rodada> listaRodada = query.getResultList();

			return listaRodada;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Rodada findProximaRodada(Campeonato campeonato) {
		Calendar horaAtual = new GregorianCalendar();
		horaAtual.setTime(new Date());
		Query query = em
		.createQuery("SELECT r FROM Rodada r JOIN r.partidas p" +
				" WHERE r.campeonato = :campeonato " +
				"AND :horaAtual < p.dataHora ORDER BY p.dataHora" );
		query.setParameter("campeonato", campeonato);
		query.setParameter("horaAtual", horaAtual);
		query.setMaxResults(1);
		return (Rodada) query.getSingleResult();
	}
}
