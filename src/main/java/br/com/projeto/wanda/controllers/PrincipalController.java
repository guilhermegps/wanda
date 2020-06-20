package br.com.projeto.wanda.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.projeto.wanda.model.dto.UsuarioUserDetails;
import br.com.projeto.wanda.services.SessionService;

@Controller
public class PrincipalController {
	@GetMapping("/")
	public String home() {
		return "/page/index";
	}

	@GetMapping("/logar")
	public String login(HttpServletResponse response) throws IOException{
		UsuarioUserDetails usuarioAtual = SessionService.usuarioAtual();
		if(usuarioAtual!=null)
	       response.sendRedirect("/");
		
		return "login";
	}

	@ResponseBody
	@GetMapping("/logar/falha")
	public Map<String, Boolean> falhaLogin(){
		Map<String, Boolean> map = new HashMap<>();
		map.put("falhou", true);
		
		return map;
	}
}
