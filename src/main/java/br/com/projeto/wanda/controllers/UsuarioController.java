package br.com.projeto.wanda.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.projeto.wanda.WLogger;
import br.com.projeto.wanda.exception.CampoInvalidoException;
import br.com.projeto.wanda.model.Usuario;
import br.com.projeto.wanda.model.dto.MenuDTO;
import br.com.projeto.wanda.model.dto.ResponseDTO;
import br.com.projeto.wanda.model.dto.UsuarioDTO;
import br.com.projeto.wanda.model.dto.UsuarioUserDetails;
import br.com.projeto.wanda.model.enums.ResponseEnum;
import br.com.projeto.wanda.services.MenuService;
import br.com.projeto.wanda.services.SessionService;
import br.com.projeto.wanda.services.UsuarioService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private MenuService menuService;

	@GetMapping("/registrar")
	public String paginaRegistro(HttpServletResponse response) throws IOException {
		UsuarioUserDetails usuarioAtual = SessionService.usuarioAtual();
		if(usuarioAtual!=null)
	       response.sendRedirect("/");
		
		return "/registro_usuario";
	}

	@ResponseBody
	@PostMapping("/registrar")
	public ResponseDTO registrar(@Valid UsuarioDTO ususario) {
		try {
			usuarioService.registrar(ususario);
			
			return ResponseEnum.SUCESSO.convertToResponseDTO(null);
		} catch (CampoInvalidoException e) {
			WLogger.error(e);
			return ResponseEnum.CAMPO_INVALIDO.convertToResponseDTO(null, Arrays.asList(e.getMessage()));
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEnum.ERRO_INTERNO.convertToResponseDTO(null, Arrays.asList(e.getMessage()));
		}
	}

	@ResponseBody
	@GetMapping("/buscar")
	public ResponseDTO buscar(UsuarioDTO filtros) {
		try {
			return ResponseEnum.SUCESSO.convertToResponseDTO(null);
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEnum.ERRO_INTERNO.convertToResponseDTO(null, Arrays.asList(e.getMessage()));
		}
	}

	@ResponseBody
	@GetMapping("/dados")
	public UsuarioDTO dados(){
		Usuario usuario = SessionService.usuarioAtual().getUsuario();
		UsuarioDTO usuarioDTO = UsuarioDTO.convert(usuario);
		List<MenuDTO> listaMenus = menuService.listarPorUsuario(usuario.getId());
		usuarioDTO.setListaMenus(listaMenus);
		return usuarioDTO;
	}
}
