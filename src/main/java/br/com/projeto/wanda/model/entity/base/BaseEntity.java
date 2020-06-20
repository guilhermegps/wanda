package br.com.projeto.wanda.model.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public abstract class BaseEntity<K extends Serializable> implements Serializable {

	public abstract K getId();
	public abstract void setId(K k);

	@Basic
	@Column(name = "data_registro", nullable = false)
	private Date dataRegistro;

	private Boolean ativo = true;
	
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@PrePersist
	public void prePersist() {
		if (this.dataRegistro == null) {
			this.dataRegistro = new Date();
		}
	}

}