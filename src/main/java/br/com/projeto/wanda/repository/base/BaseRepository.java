package br.com.projeto.wanda.repository.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.projeto.wanda.model.entity.base.BaseEntity;
import br.com.projeto.wanda.utils.FetchParametro;
import br.com.projeto.wanda.utils.QueryParametro;

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity<K>, K extends Serializable> extends JpaRepository<T, K> {

	public List<T> findAllByAttr(String attr, Object value);
	/**
	 * busca por um atributo de determinada classe
	 * @param attr
	 * @param value
	 * @param clazz
	 * @return o primeiro encontrado
	 */
	public <TT extends BaseEntity<?>> Optional<TT> findByAttr(String attr, Object value, Class<TT> clazz);
	
	/**
	 * busca por um atributo
	 * @param attr
	 * @param value
	 * @return o primeiro encontrado
	 */
	public Optional<T> findByAttr(String attr, Object value);
	
	/**
	 * busca por um atributo de determinada classe levantando exceção caso tenha mais de 1
	 * @param attr
	 * @param value
	 * @return
	 * @throws WandaException caso tenha mais de 1 registro
	 */
	public Optional<T> findByAttrStrict(String attr, Object value);
	
	/**
	 * busca por um atributo de determinada classe levantando exceção caso tenha mais de 1
	 * @param attr
	 * @param value
	 * @param clazz
	 * @return
	 * @throws WandaException caso tenha mais de 1 registro
	 */
	public <TT extends BaseEntity<?>> Optional<TT> findByAttrStrict(String attr, Object value, Class<TT> clazz);
	
	public T findOneByAttrFetchInnerJoin(String attr, Object value, String... fetchs);
	public List<T> findByAttrFetchInnerJoin(String attr, Object value, String... fetchs);
	public T findByIdFetchInnerJoin(K id, String... fetchs);
	
	public List<T> autocomplete(String campo, String termo);
//	public Page<T> pesquisar(T entidade, PageRequest paginacao);
//	public Page<T> pesquisar(T entidade, PageRequest paginacao, FetchParametro... fetchs);
//	public Page<T> pesquisar(T entidade, PageRequest paginacao, List<Order> orders, FetchParametro... fetchs);
	
	public List<T> listar(FetchParametro... fetchs);
	public Page<T> listar(int pagina, int max);
	public Page<T> listar(PageRequest paginacao);
	
	public Page<T> listar(PageRequest paginacao, FetchParametro... fetchs);
	public List<T> listarPorAtributo(List<QueryParametro> asList, FetchParametro... fetch);
	public Page<T> listarPorAtributo(PageRequest paginacao, List<QueryParametro> atributos, List<Order> orders,
			FetchParametro... fetchs);
	
	
	public T recuperarPorAtributo(List<QueryParametro> atributos);
    public T obterPorId(Serializable id, FetchParametro... fetchs);
	T findByIdFetchLeftJoin(K id, String[] fetchs);
        	
}
