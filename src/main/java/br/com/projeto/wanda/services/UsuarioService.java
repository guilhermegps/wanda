package br.com.projeto.wanda.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import br.com.projeto.wanda.exception.CampoInvalidoException;
import br.com.projeto.wanda.model.Usuario;
import br.com.projeto.wanda.model.dto.UsuarioDTO;
import br.com.projeto.wanda.model.enums.TipoEventoEnum;
import br.com.projeto.wanda.repository.UsuarioRepository;
import br.com.projeto.wanda.repository.base.BaseRepository;
import br.com.projeto.wanda.services.base.AbstractBaseService;
import br.com.projeto.wanda.utils.EncryptUtils;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
@RequestScope
public class UsuarioService extends AbstractBaseService<Usuario, UUID> {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PerfilService perfilService;
	@Autowired
	private EventoService eventoService; 

	@Override
	protected BaseRepository<Usuario, UUID> getBaseRepository() {
		return usuarioRepository;
	}

	public Usuario recuperarAtivoPorLogin(String username) {
		return usuarioRepository.recuperarAtivoPorLogin(username);
	}

	@Transactional
	public void registrar(UsuarioDTO usuarioDTO) {
		if(usuarioDTO==null)
			throw new CampoInvalidoException("Não foram informados os dados do usuário");
		
		Usuario usuario = usuarioDTO.convertToEntity();
		usuario.setSenha(EncryptUtils.passwordEnconded(usuarioDTO.getSenha()));
		usuario.setPerfil(perfilService.perfilDefault());
		
		save(usuario);
		
		eventoService.registrar(usuario, TipoEventoEnum.INCLUSAO_REGISTRO, "Cadastro de novo usuário pela tela pública");
	}

}
