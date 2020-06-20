package br.com.projeto.wanda.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import br.com.projeto.wanda.model.entity.base.UUIDEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_funcionalidade_pagina")
@GenericGenerator(name = "uuid2", strategy = "uuid2")
public class FuncionalidadePagina extends UUIDEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionalidade", nullable = false, referencedColumnName = "id")
	private Funcionalidade funcionalidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pagina", nullable = false, referencedColumnName = "id")
	private Pagina pagina;
}