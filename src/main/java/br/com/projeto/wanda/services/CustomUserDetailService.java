package br.com.projeto.wanda.services;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.projeto.wanda.utils.EncryptUtils;

@Component
public class CustomUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		
		return new org.springframework.security.core.userdetails.User("name", EncryptUtils.passwordEnconded("password"), authorityList);
	}

}
