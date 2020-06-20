package br.com.projeto.wanda.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.projeto.wanda.WLogger;
import br.com.projeto.wanda.exception.CampoInvalidoException;
import br.com.projeto.wanda.model.Evento;
import br.com.projeto.wanda.model.dto.ResponseDTO;
import br.com.projeto.wanda.model.enums.ResponseEnum;
import br.com.projeto.wanda.services.EventoService;
import br.com.projeto.wanda.services.SessionService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Controller
@RequestMapping("/evento")
public class EventoController {
	@Autowired
	private EventoService eventoService;

	@ResponseBody
	@GetMapping("/")
	public ResponseDTO paginaRegistro() throws IOException {
		List<Evento> eventos = eventoService.findAll();
		
		return ResponseEnum.SUCESSO.convertToResponseDTO(eventos);
	}

	@ResponseBody
	@GetMapping("/registrar")
	public ResponseDTO registrar() {
		try {
			Evento evento = Evento.builder()
			.descricao("Teste de evento")
			.ipUsuario("192.168.10.15")
			.usuario(SessionService.usuarioAtual().getUsuario())
			.build();
			evento.setAtivo(true);
			eventoService.save(evento);
			
			return ResponseEnum.SUCESSO.convertToResponseDTO(null);
		} catch (CampoInvalidoException e) {
			WLogger.error(e);
			return ResponseEnum.CAMPO_INVALIDO.convertToResponseDTO(null, Arrays.asList(e.getMessage()));
		} catch (Exception e) {
			WLogger.error(e);
			return ResponseEnum.ERRO_INTERNO.convertToResponseDTO(null, Arrays.asList(e.getMessage()));
		}
	}
}
