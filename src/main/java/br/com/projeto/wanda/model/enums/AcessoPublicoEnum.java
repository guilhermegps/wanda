package br.com.projeto.wanda.model.enums;

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
    
    public static boolean contains(String url) {
    	for (AcessoPublicoEnum acesso : values())
			if(acesso.getUrl().equals(url))
				return true;
    	
    	return false;
    }
}
