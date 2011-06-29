package dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import pojo.Usuario;

public class UsuarioDao extends AbstractDao<Usuario> {
	
	public UsuarioDao() {
		super();
	}
	
	
	public boolean verificaLoginExistente(String login)  {
		String q = "FROM Usuario u WHERE u.login = :login ";
		Query query = em.createQuery(q);
		query.setParameter("login", login);
		
		if (query.getResultList().size() > 0) {
			return true;
		}

		return false;
	}
		
	public Usuario findByLoginSenha(Usuario usuario) throws NoResultException{
		String q = "FROM Usuario u WHERE u.login = :login AND u.senha = :senha";
		Query query = em.createQuery(q);
		query.setParameter("login", usuario.getLogin());
		query.setParameter("senha", usuario.getSenha());
		try{
			Usuario usuLogin = (Usuario) query.getSingleResult();
			return usuLogin;
		} catch (NoResultException e) {
			throw new NoResultException();
		}			
				
	}
	
}
