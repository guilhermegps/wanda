package br.com.projeto.wanda.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import br.com.projeto.wanda.model.entity.base.UUIDEntityWithCodigo;
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
@Table(name="tb_menu")
@GenericGenerator(name = "uuid2", strategy = "uuid2")
public class Menu extends UUIDEntityWithCodigo {
	
	@Basic
	@Column(length = 50, nullable = false)
	private String descricao;
	
	@Basic
	private Integer ordem;
	
	@Basic
	@Column(length = 50)
	private String icone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionalidade", referencedColumnName = "id")
	private Funcionalidade funcionalidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_menu_pai", referencedColumnName = "id")
	private Menu menuPai;

}
