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

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_usuario")
@GenericGenerator(name = "uuid2", strategy = "uuid2")
public class Usuario extends UUIDEntityWithCodigo {
	
	@Basic
	@Column(length = 50, nullable = false)
	private String login;

	@Basic
	@Column(length = 100, nullable = false)
	private String nome;

	@Basic
	@Column(length = 11, nullable = false)
	private String cpf;
	
	@Basic
	@Column(length = 60, nullable = false)
	private String senha;
	
	@Basic
	@Column(length = 50, nullable = false)
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;
}
