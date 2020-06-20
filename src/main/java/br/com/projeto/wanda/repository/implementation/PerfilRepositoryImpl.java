package br.com.projeto.wanda.repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.projeto.wanda.model.Perfil;
import br.com.projeto.wanda.repository.PerfilRepositoryCustom;

public class PerfilRepositoryImpl implements PerfilRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Perfil> listarPerfilPorUrl(String url) {
		String hql = "select distinct p from Perfil p"
				+ " left join fetch p.paginasPerfil pp"
				+ " left join fetch pp.pagina pg"
				+ " left join fetch pg.funcionalidadesPagina fp"
				+ " left join fetch fp.funcionalidade f_pg"
				+ " left join fetch p.funcionalidadesPerfil fpf"
				+ " left join fetch fpf.funcionalidade f_p"
				+ " where p.ativo = true"
				+ " and ("
						+ " (pg.ativo = true and pg.url = :url)"
						+ " or (f_pg.ativo = true and f_pg.url = :url)"
						+ " or (f_p.ativo = true and f_p.url = :url)"
				+ ")";
		
		Query query = entityManager.createQuery(hql);
        query.setParameter("url", url);
        
        return query.getResultList();
	}

}
