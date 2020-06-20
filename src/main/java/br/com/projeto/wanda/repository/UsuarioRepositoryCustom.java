package br.com.projeto.wanda.repository;

import org.springframework.stereotype.Repository;

import br.com.projeto.wanda.model.Usuario;

@Repository
public interface UsuarioRepositoryCustom {
	
	Usuario recuperarAtivoPorLogin(String login);

}
