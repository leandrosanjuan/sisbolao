package dao;

import javax.persistence.Query;

import pojo.Campeonato;

public class CampeonatoDao extends AbstractDao<Campeonato> {
	public CampeonatoDao() {
		super();
	}

	public boolean existeCampeonato(String nome) {
		Query query = em.createQuery("FROM Campeonato where nome = :nome");
		query.setParameter("nome", nome);
		if (query.getResultList().size() > 0) {
			return true;
		}
		return false;
		
		
	}
}
