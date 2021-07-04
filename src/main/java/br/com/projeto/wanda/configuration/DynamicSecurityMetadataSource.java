package br.com.projeto.wanda.configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import br.com.projeto.wanda.model.Perfil;
import br.com.projeto.wanda.model.enums.AcessoPublicoEnum;
import br.com.projeto.wanda.services.PerfilService;

@Component
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private PerfilService perfilService;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
 
        String urlWithoutContextPath = request.getRequestURI().substring(
                request.getContextPath().length());
 
        List<Perfil> lista = perfilService.listarPerfilPorUrl(urlWithoutContextPath);
 
        if (lista!=null && !lista.isEmpty()) {
            return lista.stream()
                    .map(this::configAttribute).collect(Collectors.toList());
        } else if (AcessoPublicoEnum.allow(urlWithoutContextPath)) {
        	return null;
        }
 
        return Arrays.asList(new ConfigAttribute() {
			@Override
			public String getAttribute() {
				return "ROLE_UNAUTHORIZED";
			}
		});
	}
 
    private ConfigAttribute configAttribute(Perfil perfil) {
        return new ConfigAttribute() {        	
            @Override
            public String getAttribute() {
                return "ROLE_" + perfil.getId().toString().substring(0, 8).toUpperCase();
            }
        };
    }

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
