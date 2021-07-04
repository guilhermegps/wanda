package br.com.projeto.wanda.model.enums;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AcessoPublicoEnum {
	LOGAR("/logar"),
	LOGIN_PAGE("/login.html"),
	LOGOUT("/logout"),
	FALHA_LOGAR("/logar/falha"),
	REGISTRAR_USUARIO_PAGE("/usuario/registrar"),
	REGISTRAR_USUARIO("/registro_usuario")
	;
	
	private String url;
    
    public static boolean allow(String url) {
    	for (AcessoPublicoEnum acesso : values())
			if(StringUtils.isNotBlank(url) 
					&& url.matches(acesso.getUrl()))
				return true;
    	
    	return false;
    }
}
