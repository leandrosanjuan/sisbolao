package dao;

import java.util.List;

import javax.persistence.Query;

import pojo.Bolao;
import pojo.Usuario;

public class BolaoDao extends AbstractDao<Bolao> {

	public List<Bolao> findByParticipant(Usuario participante) {
		Query query = em
				.createQuery("FROM Bolao b WHERE :participante MEMBER OF b.usuarios OR :participante =  b.usuarioDono");
		query.setParameter("participante", participante);
		List<Bolao> boloes = query.getResultList();
		return boloes;
	}

}
