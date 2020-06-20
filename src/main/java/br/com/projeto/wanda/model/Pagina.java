package br.com.projeto.wanda.model;

import java.util.Set;

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
@Table(name="tb_pagina")
@GenericGenerator(name = "uuid2", strategy = "uuid2")
public class Pagina extends UUIDEntityWithCodigo {
	
	@Basic
	@Column(length = 50, nullable = false)
	private String descricao;

	@Basic
	@Column(length = 50, nullable = false)
	private String url;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pagina")
	private Set<FuncionalidadePagina> funcionalidadesPagina;
}
