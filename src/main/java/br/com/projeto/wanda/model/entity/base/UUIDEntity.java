package br.com.projeto.wanda.model.entity.base;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class UUIDEntity extends BaseEntity<UUID>{

	@Id
	@Type(type = "pg-uuid")
	@GeneratedValue(generator="uuid2")
	protected UUID id;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}
