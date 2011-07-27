package dao;

import java.util.List;

import javax.persistence.Query;

import pojo.Categoria;
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

	public int updateCategoriaDefault(Categoria categoria,
			Categoria categoriaDefault) {
		Query query = em
				.createQuery("UPDATE Time t SET t.categoria = :categoriaDefault "
						+ "WHERE t.categoria = categoria");
		query.setParameter("categoriaDefault", categoriaDefault);
		query.setParameter("categoria", categoria);

		return query.executeUpdate();
	}

	public List<Time> findByCategoria(Categoria categoria) {
		try {
			Query query = em
					.createQuery("FROM Time t WHERE t.categoria.id = ?1 order by t.nome");
			query.setParameter(1, categoria.getId());
			List<Time> listaTime = query.getResultList();

			return listaTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
