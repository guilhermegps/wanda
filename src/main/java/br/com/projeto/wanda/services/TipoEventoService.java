package br.com.projeto.wanda.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.projeto.wanda.model.TipoEvento;
import br.com.projeto.wanda.repository.TipoEventoRepository;
import br.com.projeto.wanda.repository.base.BaseRepository;
import br.com.projeto.wanda.services.base.AbstractBaseService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
@RequestScope
public class TipoEventoService extends AbstractBaseService<TipoEvento, UUID> {

	@Autowired
	private TipoEventoRepository tipoEventoRepository;

	@Override
	protected BaseRepository<TipoEvento, UUID> getBaseRepository() {
		return tipoEventoRepository;
	}

}
