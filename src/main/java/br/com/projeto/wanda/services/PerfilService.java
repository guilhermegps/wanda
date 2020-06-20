package br.com.projeto.wanda.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.projeto.wanda.model.Perfil;
import br.com.projeto.wanda.repository.PerfilRepository;
import br.com.projeto.wanda.repository.base.BaseRepository;
import br.com.projeto.wanda.services.base.AbstractBaseService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
@RequestScope
public class PerfilService extends AbstractBaseService<Perfil, UUID> {

	@Autowired
	private PerfilRepository perfilRepository;

	@Override
	protected BaseRepository<Perfil, UUID> getBaseRepository() {
		return perfilRepository;
	}
	
	public List<Perfil> listarPerfilPorUrl(String url) {
		return perfilRepository.listarPerfilPorUrl(url);
	}
	
	/**
	 * Irá consultar na base de dados e retornar o Perfil default "USER", caso não encontre retornará null. 
	 * 
	 * @return Perfil
	 */
	public Perfil perfilDefault() {
		Optional<Perfil> perfil = perfilRepository.findById(UUID.fromString("e0bdf978-646c-4580-92a3-a631b78ec88b"));
		return (perfil.isPresent()) ? perfil.get() : null;
	}

}
