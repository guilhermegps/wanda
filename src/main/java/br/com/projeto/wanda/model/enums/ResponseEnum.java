package br.com.projeto.wanda.model.enums;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.projeto.wanda.model.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que lista os responses padrões da aplicação.
 * Embora baseado nos Status do HTTP, pode conter códigos e mensagens customizadas da aplicação.
 * 
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {
	CAMPO_INVALIDO(11, "Não Encontrado"),
	SUCESSO(200, "Sucesso"),
	NAO_ENCONTRADO(404, "Não Encontrado"),
	ERRO_INTERNO(500, "Erro Interno no Servidor");
	
	private int codigo;
	private String mensagem;
	
	public ResponseDTO convertToResponseDTO(Object valor) {
		return ResponseDTO.builder()
				.codigo(codigo)
				.valor(valor)
				.mensagens(Arrays.asList(mensagem))
				.build();
	}
	
	public ResponseDTO convertToResponseDTO(Object valor, List<String> mensagens) {
		return ResponseDTO.builder()
				.codigo(codigo)
				.valor(valor)
				.mensagens(mensagens)
				.build();
	}
}
