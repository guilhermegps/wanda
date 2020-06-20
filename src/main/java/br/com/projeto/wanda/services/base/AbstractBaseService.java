package br.com.projeto.wanda.services.base;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.com.projeto.wanda.model.entity.base.BaseEntity;
import br.com.projeto.wanda.repository.base.BaseRepository;
import br.com.projeto.wanda.utils.FetchParametro;

public abstract class AbstractBaseService <T extends BaseEntity<K>, K extends Serializable> {

	protected abstract BaseRepository<T, K> getBaseRepository();
	
	public T save(T entity) {
		return getBaseRepository().save(entity);
	}

	public Optional<T> findOne(Example<T> example) {
		return getBaseRepository().findOne(example);
	}

	public Page<T> findAll(Pageable pageable) {
		return getBaseRepository().findAll(pageable);
	}

	public List<T> findAll() {
		return getBaseRepository().findAll();
	}

	public List<T> findAll(Sort sort) {
		return getBaseRepository().findAll(sort);
	}

	public Optional<T> findById(K id) {
		return getBaseRepository().findById(id);
	}
	
	public List<T> findByAttrFecthInnerJoin(String attr, Object value, String... fetchs) {
		return getBaseRepository().findByAttrFetchInnerJoin(attr, value, fetchs);
	}
	
	public T findOneByAttrFetchInnerJoin(String attr, Object value, String... fetchs) {
		return getBaseRepository().findOneByAttrFetchInnerJoin(attr, value, fetchs); 
	}
	
	public T findByIdFetchLeftJoin(K id, String... fetchs) {
		return getBaseRepository().findByIdFetchLeftJoin(id, fetchs);
	}
	
	public T findByIdFetchInnerJoin(K id, String... fetchs) {
		return getBaseRepository().findByIdFetchInnerJoin(id, fetchs);
	}

	public List<T> findAllById(Iterable<K> ids) {
		return getBaseRepository().findAllById(ids);
	}

	public  List<T> saveAll(Iterable<T> entities) {
		return getBaseRepository().saveAll(entities);
	}

	public boolean existsById(K id) {
		return getBaseRepository().existsById(id);
	}

	public void flush() {
		getBaseRepository().flush();
	}

	public T saveAndFlush(T entity) {
		return getBaseRepository().saveAndFlush(entity);
	}

	public void deleteInBatch(Iterable<T> entities) {
		getBaseRepository().deleteInBatch(entities);
	}

	public  Page<T> findAll(Example<T> example, Pageable pageable) {
		return getBaseRepository().findAll(example, pageable);
	}

	public long count() {
		return getBaseRepository().count();
	}

	public void deleteAllInBatch() {
		getBaseRepository().deleteAllInBatch();
	}

	public void deleteById(K id) {
		getBaseRepository().deleteById(id);
	}

	public T getOne(K id) {
		return getBaseRepository().getOne(id);
	}

	public void delete(T entity) {
		getBaseRepository().delete(entity);
	}

	public  long count(Example<T> example) {
		return getBaseRepository().count(example);
	}

	public void deleteAll(Iterable<? extends T> entities) {
		getBaseRepository().deleteAll(entities);
	}

	public  List<T> findAll(Example<T> example) {
		return getBaseRepository().findAll(example);
	}

	public  boolean exists(Example<T> example) {
		return getBaseRepository().exists(example);
	}

	public void deleteAll() {
		getBaseRepository().deleteAll();
	}

	public  List<T> findAll(Example<T> example, Sort sort) {
		return getBaseRepository().findAll(example, sort);
	}

	public List<T> findAllByAttr(String attr, Object value) {
		return getBaseRepository().findAllByAttr(attr, value);
	}
	
	public List<T> autocomplete(String campo, String termo) {
		return getBaseRepository().autocomplete(campo, termo);
	}
	
	/**
	 * Pesquisa e retorna 1 pelo atributo
	 * @param attr
	 * @param value
	 * @return T encontrado ou null, WandaException se mais de 1 elemento
	 */
	public T findOneByAttr(String attr, Object value) {
		return getBaseRepository().findByAttrStrict(attr, value).orElse(null);
	}

//	public Page<T> pesquisar(T entidade, PageRequest paginacao) {
//		return getBaseRepository().pesquisar(entidade, paginacao);
//	}
//	
//	public Page<T> pesquisar(T entidade, PageRequest paginacao, FetchParametro... fetchs) {
//		return getBaseRepository().pesquisar(entidade, paginacao, fetchs);
//	}

	public List<T> listar(FetchParametro... fetchs) {
		return getBaseRepository().listar(fetchs);
	}
	public Page<T> listar(int pagina, int max) {
		return getBaseRepository().listar(pagina, max);
	}
	public Page<T> listar(PageRequest paginacao) {
		return getBaseRepository().listar(paginacao);
	}
	
	public Page<T> listar(PageRequest paginacao, FetchParametro... fetchs){
		return getBaseRepository().listar(paginacao, fetchs);
	}
	
}
