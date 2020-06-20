package br.com.projeto.wanda.services;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.projeto.wanda.model.dto.UsuarioUserDetails;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
public class SessionService {
	
	public static UsuarioUserDetails usuarioAtual(){
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        return principal instanceof UsuarioUserDetails ? (UsuarioUserDetails) principal : null;
	    }
	    return null;
	}

	
	/**
	 * Irá retornar o IP da requisição.
	 * Para que ele retorne sempre um Ipv4 é necessário configurar na JVM -Djava.net.preferIPv4Stack=true
	 * 
	 * @return String ip
	 */
	public static String getRequestIp() {
		HttpServletRequest request = 
		        ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
		                .getRequest();
        String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isBlank(remoteAddr))
            remoteAddr = request.getRemoteAddr();

        return remoteAddr;
    }
}
