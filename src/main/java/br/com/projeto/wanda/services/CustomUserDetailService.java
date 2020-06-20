package br.com.projeto.wanda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.wanda.model.Usuario;
import br.com.projeto.wanda.model.dto.UsuarioUserDetails;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Component
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.recuperarAtivoPorLogin(username);
        if (usuario == null) throw new UsernameNotFoundException(username);

        return new UsuarioUserDetails(usuario, SessionService.getRequestIp());
	}
}
