package dao;

import javax.persistence.Query;

import pojo.Time;

public class TimeDao extends AbstractDao<Time> {
	public TimeDao() {
		super();
	}
	public boolean existeTime(String nome) {
		Query query = em.createQuery("FROM Time where nome = :nome");
		query.setParameter("nome", nome);
		if (query.getResultList().size() > 0) {
			return true;
		}
		return false;
		
		
	}
	
}
