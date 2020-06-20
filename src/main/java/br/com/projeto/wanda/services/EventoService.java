package br.com.projeto.wanda.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import br.com.projeto.wanda.model.Evento;
import br.com.projeto.wanda.model.EventoUsuario;
import br.com.projeto.wanda.model.Usuario;
import br.com.projeto.wanda.model.dto.UsuarioUserDetails;
import br.com.projeto.wanda.model.enums.TipoEventoEnum;
import br.com.projeto.wanda.repository.EventoRepository;
import br.com.projeto.wanda.repository.base.BaseRepository;
import br.com.projeto.wanda.services.base.AbstractBaseService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
@RequestScope
public class EventoService extends AbstractBaseService<Evento, UUID> {

	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private EventoUsuarioService eventoUsuarioService;

	@Override
	protected BaseRepository<Evento, UUID> getBaseRepository() {
		return eventoRepository;
	}

	@Transactional
	public void registrar(Usuario usuario, TipoEventoEnum tipoEvento, String descricao) {
		UsuarioUserDetails usuarioAtual = SessionService.usuarioAtual();
		Usuario userEvento = (usuarioAtual!=null) ? usuarioAtual.getUsuario() : usuario;
		Evento evento = Evento.builder()
				.descricao(descricao)
				.ipUsuario(SessionService.getRequestIp())
				.tipoEvento(tipoEvento.convertToEntityWithId())
				.usuario(userEvento)
				.build();
		save(evento);
		
		eventoUsuarioService.save(new EventoUsuario(evento, usuario));
	}

}
