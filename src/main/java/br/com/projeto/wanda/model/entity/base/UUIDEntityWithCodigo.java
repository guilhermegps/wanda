package br.com.projeto.wanda.model.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class UUIDEntityWithCodigo extends UUIDEntity {

	@Column(name = "codigo", nullable = false, insertable = false, columnDefinition="serial", unique = true)
	protected Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
