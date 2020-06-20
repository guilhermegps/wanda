package br.com.projeto.wanda.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.projeto.wanda.model.Evento;
import br.com.projeto.wanda.repository.base.BaseRepository;

@Repository
public interface EventoRepository extends BaseRepository<Evento, UUID> {

}
