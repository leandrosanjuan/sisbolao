package dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;

import pojo.Usuario;

import util.PersistenceUtil;

public class AbstractDao<T> {
	
	protected EntityManager em;

	public AbstractDao() {
		super();
		this.em = PersistenceUtil.getEntityManager();
		
	}
	public void create(T objeto) throws Exception {
		try {
			em.getTransaction().begin();
				em.persist(objeto);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception();
		}
	}
	public void update(T objeto) throws Exception {
		try {
			em.getTransaction().begin();
				em.merge(objeto);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception();
		}
	}
	public void delete(T objeto)  throws Exception {
		try {
			em.getTransaction().begin();
				em.remove(objeto);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception();
		}
	}
	@SuppressWarnings("unchecked")
	public Collection<T> findAll(Class<T> clazz) {
		try {
			String entityName = clazz.getName();
			Query query = em.createQuery("FROM " + entityName);
			Collection<T> result = (Collection<T>) query.getResultList();
			return result;
		} catch (NoResultException e) {
			return new ArrayList<T>();
		}
	}

	public <K> T findById(Class<T> clazz, K id) {
		return em.find(clazz, id);
	}

	public boolean isLoaded(T entity) {
		PersistenceUnitUtil util = em.getEntityManagerFactory()
				.getPersistenceUnitUtil();
		return util.isLoaded(entity);
	}

	public boolean isLoaded(T entity, String attributeName) {
		PersistenceUnitUtil util = em.getEntityManagerFactory()
				.getPersistenceUnitUtil();
		return util.isLoaded(entity, attributeName);
	}

	public boolean isManaged(T entity) {
		return em.contains(entity);
	}
}
