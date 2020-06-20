package br.com.projeto.wanda.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
@Table(name="tb_perfil")
@GenericGenerator(name = "uuid2", strategy = "uuid2")
public class Perfil extends UUIDEntityWithCodigo {
	
	public Perfil(UUID id) {
		this.id = id;
	}

	@Basic
	@Column(length = 50, nullable = false)
	private String descricao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private Set<PaginaPerfil> paginasPerfil;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private Set<FuncionalidadePerfil> funcionalidadesPerfil;
}
