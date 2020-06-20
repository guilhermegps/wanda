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
@Table(name="tb_pagina_perfil")
@GenericGenerator(name = "uuid2", strategy = "uuid2")
public class PaginaPerfil extends UUIDEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pagina", insertable = false, updatable = false, nullable = false, referencedColumnName = "id")
	private Pagina pagina;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil", insertable = false, updatable = false, nullable = false, referencedColumnName = "id")
	private Perfil perfil;
}
