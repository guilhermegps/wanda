package br.com.projeto.wanda.repository.implementation;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.jdbc.object.SqlQuery;

import br.com.projeto.wanda.WLogger;
import br.com.projeto.wanda.model.dto.MenuDTO;
import br.com.projeto.wanda.repository.MenuRepositoryCustom;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
public class MenuRepositoryImpl implements MenuRepositoryCustom{
    @PersistenceContext
    EntityManager entityManager;

	@Override
	public List<MenuDTO> listarPorUsuario(UUID idUsuario) {
		String sql = "select distinct m.codigo,"
				+ " m.descricao,"
				+ " m.ordem,"
				+ " m.icone,"
				+ " f.url,"
				+ " mp.codigo as cod_menu_pai,"
				+ " mp.descricao as desc_menu_pai,"
				+ " mp.icone as icone_menu_pai,"
				+ " mp.ordem as ordem_menu_pai"
			+ " from tb_menu m "
			+ " left join tb_funcionalidade_perfil f_p on(f_p.id_funcionalidade = m.id_funcionalidade)"
			+ " left join tb_funcionalidade_pagina f_pg on(f_pg.id_funcionalidade = m.id_funcionalidade)"
			+ " left join tb_pagina_perfil pp on(pp.id_pagina = f_pg.id_pagina)"
			+ " left join tb_menu mp on(mp.id = m.id_menu_pai)"
			+ " inner join tb_perfil p on(p.id = f_p.id_perfil or p.id = pp.id_perfil)"
			+ " inner join tb_funcionalidade f on(f.id = f_p.id_funcionalidade or f.id = f_pg.id_funcionalidade)"
			+ " inner join tb_usuario u on(u.id_perfil = p.id)"
			+ " where p.ativo = true"
			+ " and f.ativo = true"
			+ " and u.id = :idUsuario"
			+ " order by mp.ordem, m.ordem";
		
		Query query = entityManager.createNativeQuery(sql, MenuDTO.class);
		query.setParameter("idUsuario", idUsuario);
        try {
        	 return query.getResultList();
        } catch(NoResultException e) {
        	WLogger.warn(e);
        }
        
        return null;
	}

}
