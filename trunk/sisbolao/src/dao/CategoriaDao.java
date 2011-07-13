package dao;

import javax.persistence.Query;

import pojo.Categoria;

public class CategoriaDao extends AbstractDao<Categoria> {

	public boolean existeCategoria(String nome) {
		Query query = em.createQuery("FROM Categoria where nome = :nome");
		query.setParameter("nome", nome);
		if (query.getResultList().size() > 0) {
			return true;
		}
		return false;

	}

	public Categoria findByName(String nome) {
		Query query = em.createQuery("FROM Categoria where nome = :nome");
		query.setParameter("nome", nome);
		return (Categoria) query.getSingleResult();
	}
}
