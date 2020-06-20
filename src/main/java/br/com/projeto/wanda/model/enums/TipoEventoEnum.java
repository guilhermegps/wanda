package br.com.projeto.wanda.model.enums;

import java.util.UUID;

import br.com.projeto.wanda.model.TipoEvento;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Getter
@AllArgsConstructor
public enum TipoEventoEnum {
	INCLUSAO_REGISTRO(UUID.fromString("359478a8-f1f6-486e-bdc2-504bbead5fbc"), 1L, "Inclusão de Registro"),
	ALTERACAO_REGISTRO(UUID.fromString("e2f4f40c-0340-4404-ad32-1a14692de709"), 2L, "Alteração de Registro"),
	DESATIVACAO_REGISTRO(UUID.fromString("d3320a32-b803-40f9-ab35-13c29833f214"), 3L, "Desativação de Registro"),
	REMOCAO_REGISTRO(UUID.fromString("a6c4efd6-0407-4bfb-9400-dd6618b0b069"), 4L, "Remoção de Registro");
	
	private UUID id;
	private Long codigo;
	private String descricao;
	
	public TipoEvento convertToEntityWithId() {
		TipoEvento te = new TipoEvento();
		te.setId(id);
		return te;
	}
}
