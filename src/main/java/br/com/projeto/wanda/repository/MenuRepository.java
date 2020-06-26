package br.com.projeto.wanda.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.projeto.wanda.model.Menu;
import br.com.projeto.wanda.repository.base.BaseRepository;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Repository
public interface MenuRepository extends BaseRepository<Menu, UUID>, MenuRepositoryCustom {

}
