package br.com.projeto.wanda.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.projeto.wanda.model.Menu;
import br.com.projeto.wanda.model.dto.MenuDTO;
import br.com.projeto.wanda.repository.MenuRepository;
import br.com.projeto.wanda.repository.base.BaseRepository;
import br.com.projeto.wanda.services.base.AbstractBaseService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
@RequestScope
public class MenuService extends AbstractBaseService<Menu, UUID> {
	@Autowired
	private MenuRepository menuRepository;

	@Override
	protected BaseRepository<Menu, UUID> getBaseRepository() {
		return menuRepository;
	}
	
	public List<MenuDTO> listarPorUsuario(UUID idUsuario){
		return menuRepository.listarPorUsuario(idUsuario);
	}

}
