package br.com.projeto.wanda.model.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.projeto.wanda.model.Usuario;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
public class UsuarioUserDetails implements UserDetails {
	
	private Usuario usuario;
	private String ip;
	private String username;
	private String password;
	private boolean enabled;
	private Collection<GrantedAuthority> authorities;
	
	public UsuarioUserDetails() {}
	
	public UsuarioUserDetails(Usuario usuario, String ip) {
		if(usuario!=null) {
			this.username = usuario.getLogin();
			this.password = usuario.getSenha();
			this.enabled = usuario.getAtivo();
			this.usuario = usuario;
			
			this.authorities = new HashSet<>();
			this.authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().getId().toString().substring(0, 8).toUpperCase()));
		}
		this.ip = ip;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getIp() {
		return ip;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
