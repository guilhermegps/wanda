package br.com.projeto.wanda.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.projeto.wanda.model.EventoUsuario;
import br.com.projeto.wanda.repository.EventoUsuarioRepository;
import br.com.projeto.wanda.repository.base.BaseRepository;
import br.com.projeto.wanda.services.base.AbstractBaseService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
@RequestScope
public class EventoUsuarioService extends AbstractBaseService<EventoUsuario, UUID> {

	@Autowired
	private EventoUsuarioRepository eventoUsuarioRepository;

	@Override
	protected BaseRepository<EventoUsuario, UUID> getBaseRepository() {
		return eventoUsuarioRepository;
	}

}
