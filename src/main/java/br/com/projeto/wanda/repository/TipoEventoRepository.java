package br.com.projeto.wanda.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.projeto.wanda.model.TipoEvento;
import br.com.projeto.wanda.repository.base.BaseRepository;

@Repository
public interface TipoEventoRepository extends BaseRepository<TipoEvento, UUID> {

}
