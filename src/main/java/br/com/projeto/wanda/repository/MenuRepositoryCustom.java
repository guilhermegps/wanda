package br.com.projeto.wanda.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.projeto.wanda.model.dto.MenuDTO;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Repository
public interface MenuRepositoryCustom {
	
	public List<MenuDTO> listarPorUsuario(UUID idUsuario);

}
