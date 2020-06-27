package br.com.projeto.wanda.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MenuDTO implements Serializable {
	@Id
	private Long codigo;
	private String descricao;
	private Integer ordem;
	private String icone;
	private String url;
	
	private Long codMenuPai;
	private String descMenuPai;
	private Integer ordemMenuPai;
	private String iconeMenuPai;
	
}
