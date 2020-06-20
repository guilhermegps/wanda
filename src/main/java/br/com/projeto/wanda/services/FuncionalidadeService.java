package br.com.projeto.wanda.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import br.com.projeto.wanda.model.Funcionalidade;
import br.com.projeto.wanda.repository.FuncionalidadeRepository;
import br.com.projeto.wanda.repository.base.BaseRepository;
import br.com.projeto.wanda.services.base.AbstractBaseService;

@Service
@RequestScope
public class FuncionalidadeService extends AbstractBaseService<Funcionalidade, UUID> {
	@Autowired
	private FuncionalidadeRepository funcionalidadeRepository;

	@Override
	protected BaseRepository<Funcionalidade, UUID> getBaseRepository() {
		return funcionalidadeRepository;
	}
}
