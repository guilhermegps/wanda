package br.com.projeto.wanda.repository.implementation;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.projeto.wanda.WLogger;
import br.com.projeto.wanda.model.Usuario;
import br.com.projeto.wanda.repository.UsuarioRepositoryCustom;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

	@Override
	public Usuario recuperarAtivoPorLogin(String login) {
		String hql = "select u from Usuario u"
				+ " inner join fetch u.perfil p"
				+ " left join fetch p.paginasPerfil pp"
				+ " left join fetch pp.pagina pg"
				+ " left join fetch pg.funcionalidadesPagina fp"
				+ " left join fetch fp.funcionalidade f"
				+ " where u.login = :login"
				+ " and u.ativo = true";
		
		Query query = entityManager.createQuery(hql);
        query.setParameter("login", login);
        try {
        	 return (Usuario) query.getSingleResult();
        } catch(NoResultException e) {
        	WLogger.warn(e);
        }
        
        return null;
	}
}
