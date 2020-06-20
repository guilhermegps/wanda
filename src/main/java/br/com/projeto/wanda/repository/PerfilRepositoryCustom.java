package br.com.projeto.wanda.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.projeto.wanda.model.Perfil;

@Repository
public interface PerfilRepositoryCustom {

	public List<Perfil> listarPerfilPorUrl(String url);
	
}
