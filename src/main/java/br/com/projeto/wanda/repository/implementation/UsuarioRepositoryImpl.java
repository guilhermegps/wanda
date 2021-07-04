package br.com.projeto.wanda.repository.implementation;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.projeto.wanda.model.Usuario;
import br.com.projeto.wanda.repository.UsuarioRepositoryCustom;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Slf4j
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
        	log.warn(e.getMessage(), e);
        }
        
        return null;
	}
}
