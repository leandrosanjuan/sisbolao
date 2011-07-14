package dao;

import java.util.List;

import javax.persistence.Query;

import pojo.Campeonato;
import pojo.Usuario;

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

	public List<Campeonato> findByBoloesUsuario(Usuario usuario) {		
		Query query = em
				.createQuery("FROM Campeonato c WHERE EXISTS( SELECT 1 FROM Bolao b WHERE b.campeonato = c "
						+ "AND( :usuario MEMBER b.usuarios OR b.usuarioDono = :usuario))");
		query.setParameter("usuario", usuario.getId());
		
		return query.getResultList();
	}
}
