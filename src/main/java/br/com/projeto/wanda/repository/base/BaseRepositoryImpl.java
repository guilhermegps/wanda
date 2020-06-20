package br.com.projeto.wanda.repository.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.sql.JoinType;
import org.hibernate.type.BooleanType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.wanda.WLogger;
import br.com.projeto.wanda.exception.WandaException;
import br.com.projeto.wanda.model.entity.base.BaseEntity;
import br.com.projeto.wanda.utils.FetchParametro;
import br.com.projeto.wanda.utils.QueryParametro;

public class BaseRepositoryImpl<T extends BaseEntity<K>, K extends Serializable> extends SimpleJpaRepository<T, K>
		implements BaseRepository<T, K> {

	protected EntityManager entityManager;

	protected JpaEntityInformation<T, ?> entityInformation;
	
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityClass = entityInformation.getJavaType();
		this.entityInformation = entityInformation;
	}

	@Override
	public T findByIdFetchInnerJoin(K id, String... fetchs) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		for (String f : fetchs) {
			root.fetch(f, javax.persistence.criteria.JoinType.INNER);
		}
		TypedQuery<T> typedQuery;
		typedQuery = entityManager
				.createQuery(cq.select(root).where(cb.equal(root.get(entityInformation.getIdAttribute()), id)));

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public T findByIdFetchLeftJoin(K id, String... fetchs) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		for (String f : fetchs) {
			root.fetch(f, javax.persistence.criteria.JoinType.LEFT);
		}
		TypedQuery<T> typedQuery;
		typedQuery = entityManager
				.createQuery(cq.select(root).where(cb.equal(root.get(entityInformation.getIdAttribute()), id)));

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<T> findAllByAttr(String attr, Object value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);

		TypedQuery<T> typedQuery;
		if (value instanceof String) {
			typedQuery = entityManager.createQuery(
					cq.select(root).where(cb.equal(cb.lower(root.get(attr)), ((String) value).toLowerCase())));
		} else {
			typedQuery = entityManager.createQuery(cq.select(root).where(cb.equal(root.get(attr), value)));
		}
		
		List<T> rets = typedQuery.getResultList();
		return rets;
	}
	
	@Override
	/**
	 * busca por um atributo de determinada classe
	 */
	public <TT extends BaseEntity<?>> Optional<TT> findByAttr(String attr, Object value, Class<TT> clazz) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TT> cq = cb.createQuery(clazz);
		Root<TT> root = cq.from(clazz);

		TypedQuery<TT> typedQuery;
		if (value instanceof String) {
			typedQuery = entityManager.createQuery(
					cq.select(root).where(cb.equal(cb.lower(root.get(attr)), ((String) value).toLowerCase())));
		} else {
			typedQuery = entityManager.createQuery(cq.select(root).where(cb.equal(root.get(attr), value)));
		}
		
		List<TT> rets = typedQuery.getResultList();
		if (rets.size() == 0) {
			return Optional.empty();
		}
		return Optional.of(rets.get(0));
	}
	
	@Override
	/**
	 * busca por um atributo de determinada classe
	 */
	public Optional<T> findByAttr(String attr, Object value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);

		TypedQuery<T> typedQuery;
		if (value instanceof String) {
			typedQuery = entityManager.createQuery(
					cq.select(root).where(cb.equal(cb.lower(root.get(attr)), ((String) value).toLowerCase())));
		} else {
			typedQuery = entityManager.createQuery(cq.select(root).where(cb.equal(root.get(attr), value)));
		}
		
		List<T> rets = typedQuery.getResultList();
		if (rets.size() == 0) {
			return Optional.empty();
		}
		return Optional.of(rets.get(0));
		
	}
	
	@Override
	/**
	 * busca por um atributo de determinada classe levantando exceção caso tenha mais de 1
	 */
	public Optional<T> findByAttrStrict(String attr, Object value) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);

		TypedQuery<T> typedQuery;
		if (value instanceof String) {
			typedQuery = entityManager.createQuery(
					cq.select(root).where(cb.equal(cb.lower(root.get(attr)), ((String) value).toLowerCase())));
		} else {
			typedQuery = entityManager.createQuery(cq.select(root).where(cb.equal(root.get(attr), value)));
		}
		
		List<T> rets = typedQuery.getResultList();
		if (rets.size() == 0) {
			return Optional.empty();
		}
		if (rets.size() > 1) {
			throw new WandaException("mais de 1 resultado");
		}
		return Optional.of(rets.get(0));
		
	}
	
	@Override
	/**
	 * busca por um atributo de determinada classe levantando exceção caso tenha mais de 1
	 */
	public <TT extends BaseEntity<?>> Optional<TT> findByAttrStrict(String attr, Object value, Class<TT> clazz) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TT> cq = cb.createQuery(clazz);
		Root<TT> root = cq.from(clazz);

		TypedQuery<TT> typedQuery;
		if (value instanceof String) {
			typedQuery = entityManager.createQuery(
					cq.select(root).where(cb.equal(cb.lower(root.get(attr)), ((String) value).toLowerCase())));
		} else {
			typedQuery = entityManager.createQuery(cq.select(root).where(cb.equal(root.get(attr), value)));
		}
		
		List<TT> rets = typedQuery.getResultList();
		if (rets.size() == 0) {
			return Optional.empty();
		}
		if (rets.size() > 1) {
			throw new WandaException("mais de 1 resultado");
		}
		return Optional.of(rets.get(0));
		
	}

	// TODO revisar e considerar subcaminhos de fetch ex:
	// medicoEspecialidades.especialidade
	@Override
	public T findOneByAttrFetchInnerJoin(String attr, Object value, String... fetchs) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);

		for (String f : fetchs) {
			if (f.contains(".")) {
				String s = f.substring(0, f.indexOf("."));
				// root.join(s);
				root.fetch(s);
			} else {
				// root.join(f);
				root.fetch(f);
			}
		}
		TypedQuery<T> typedQuery;
		if (value instanceof String) {
			typedQuery = entityManager.createQuery(
					cq.select(root).where(cb.equal(cb.lower(root.get(attr)), ((String) value).toLowerCase())));
		} else {
			typedQuery = entityManager.createQuery(cq.select(root).where(cb.equal(root.get(attr), value)));
		}

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<T> findByAttrFetchInnerJoin(String attr, Object value, String... fetchs) {

		throw new UnsupportedOperationException();
	}

	//---------------------------------------------------------------------------------------------------------------------------------------------
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// TODO - TODOS os metodos abaixo devem ser reescritos para usarem JPA criteria
	// e remover TODOS os desnecessarios
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	/**
	 * Classe da entidade.
	 */
	private Class<T> entityClass;
	private Class<?> entityClassAlternativo;

	@Deprecated
	private Class<?> getEntityClass() {
		if (entityClassAlternativo != null) {
			return entityClassAlternativo;
		}
		return entityClass;
	}

	/**
	 * @return objeto Session do Hibernate referente ao {@link #entityManager}.
	 */

	@Deprecated
	protected Session getSession() {
		return (Session) entityManager.getDelegate();
	}

//	@Deprecated
//	public Page<T> pesquisar(T entidade, PageRequest paginacao) {
//		try {
//			Criteria criteria = getSession().createCriteria(getEntityClass());
//			criarExamples(criteria, entidade, "");
//
//			return getPage(criteria, paginacao, null, null);
//		} catch (Exception e) {
//			WLogger.error(e);
//			return null;
//		}
//	}
//
//	@Deprecated
//	public Page<T> pesquisar(T entidade, PageRequest paginacao, FetchParametro... fetchs) {
//		try {
//			Criteria criteria = getSession().createCriteria(getEntityClass());
//			criarExamples(criteria, entidade, "");
//
//			return getPage(criteria, paginacao, null, fetchs);
//		} catch (Exception e) {
//			WLogger.error(e);
//			return null;
//		}
//	}
//
//	@Deprecated
//	public Page<T> pesquisar(T entidade, PageRequest paginacao, List<Order> orders, FetchParametro... fetchs) {
//		try {
//			Criteria criteria = getSession().createCriteria(getEntityClass());
//			criarExamples(criteria, entidade, "");
//
//			return getPage(criteria, paginacao, orders, fetchs);
//		} catch (Exception e) {
//			WLogger.error(e);
//			return null;
//		}
//	}

//	@Deprecated
//	private void criarExamples(Criteria criteria, T entidade, String path) {
//		if (entidade != null) {
//			if (path.isEmpty()) {
//				criteria.add(Example.create(entidade).ignoreCase().enableLike(MatchMode.ANYWHERE)
//						.setPropertySelector(selectorNaoBooleansNulos));
//			}
//
//			Field[] campos = entidade.getClass().getDeclaredFields();
//
//			for (Field campo : campos) {
//				if (Modifier.isStatic(campo.getModifiers()) || Modifier.isTransient(campo.getModifiers())
//						|| Modifier.isFinal(campo.getModifiers()) || campo.isAnnotationPresent(Transient.class)) {
//					continue;
//				}
//				campo.setAccessible(true);
//				try {
//					Object obj = campo.get(entidade);
//					if (obj != null) {
//
//						if (obj instanceof Collection) {
//							String finalPath = campo.getName();
//							// String pth = (finalPath.isEmpty() ? campo.getName() : (".") +
//							// campo.getName());
//							Criteria crit = criteria.createCriteria(finalPath, JoinType.LEFT_OUTER_JOIN);
//							((Iterable) obj).forEach(o -> {
//								criarExamples(crit, (T) o, finalPath);
//							});
//						}
//
//						if (obj instanceof EntidadeBasica) {
//							if (EntidadeUtils.isEntidadeVazia((EntidadeBasica) obj, false)) {
//								continue;
//							}
//
//							path = campo.getName();
//							Criteria critTmp = criteria.createCriteria(path, JoinType.LEFT_OUTER_JOIN);
//							if (!EntidadeUtils.isEntidadeVazia((EntidadeBasica) obj, true)) {
//								Example criterion = Example.create(obj).ignoreCase().enableLike(MatchMode.ANYWHERE)
//										.setPropertySelector(selectorNaoBooleansNulos);
//
//								critTmp.add(criterion);
//							}
//
//							criarExamples(critTmp, (T) obj, path);
//						} else {
//							String key = getKey(entidade.getClass());
//							if (campo.getName().equals(key)) {
//								criteria.add(Restrictions.eq(key, obj));
//							} else if (obj.getClass().isEnum()) {
//								criteria.add(Restrictions.eq(campo.getName(), obj));
//							}
//						}
//					}
//				} catch (Exception e) {
//					WLogger.debug(e);
//				}
//			}
//		}
//	}

	@Deprecated
	private String getKey(Class<?> clazz) {
		return this.entityInformation.getIdAttribute().getName();
//		Field[] declaredFields = clazz.getDeclaredFields();
//		String chave = null;
//		for (Field declaredField : declaredFields) {
//			if (declaredField.isAnnotationPresent(Id.class) || declaredField.isAnnotationPresent(EmbeddedId.class)) {
//				chave = declaredField.getName();
//				break;
//			}
//		}
//		return chave;
	}

	@Deprecated
	protected Example.PropertySelector selectorNaoBooleansNulos = (valor, campo, tipo) -> {
		if (valor == null) {
			return false;
		}

		if (tipo.getClass().equals(BooleanType.class) && !(boolean) valor) {
			return false;
		}

		return true;
	};

	@Deprecated
	private Page<T> getPage(Criteria criteria, PageRequest paginacao, List<Order> orders, FetchParametro... fetchs) {
		Page<T> page;

		criteria.setProjection(Projections.rowCount());
		Long total = (Long) criteria.uniqueResult();
		if (total > 0) {
			criteria.setProjection(null);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			if (fetchs != null && fetchs.length > 0) {
				setFetchs(criteria, fetchs);
			}

			if (orders != null) {
				for (Order order : orders) {
					criteria.addOrder(order);
				}
			}

			criteria.setMaxResults(paginacao.getPageSize() > 0 ? paginacao.getPageSize() : 10);
			criteria.setFirstResult(paginacao.getPageNumber() * paginacao.getPageSize());
			page = new PageImpl<>(criteria.list(), paginacao, total);
		} else {
			page = new PageImpl<>(Collections.emptyList(), paginacao, total);
		}

		return page;
	}

	@Deprecated
	private void setFetchs(Criteria criteria, FetchParametro[] fetchs) {
		if (fetchs != null) {
			for (FetchParametro parametro : fetchs) {

				String key = parametro.getAtributo();

				if (key.contains(".")) {
					// String novaKey = key.substring(0, key.lastIndexOf("."));

					String[] alias = key.split("\\.");
					String tmpKey = "";
					for (String s : alias) {
						if (tmpKey.isEmpty()) {
							tmpKey = s;
						} else {
							tmpKey += "." + s;
						}
						// criteria.createCriteria(tmpKey, tmpKey, parametro.getJoinType());
						criteria.setFetchMode(tmpKey, parametro.getFetchMode());
					}
				} else {
					if (!existeAlias(criteria, key)) {
						criteria.createAlias(key, key, parametro.getJoinType());
					}
					criteria.setFetchMode(key, parametro.getFetchMode());
				}
			}
		}
	}

	@Deprecated
	private boolean existeAlias(Criteria criteria, String alias) {

		try {
			Field subcriteriaList = CriteriaImpl.class.getDeclaredField("subcriteriaList");
			subcriteriaList.setAccessible(true);
			List<CriteriaImpl.Subcriteria> lista = (List<CriteriaImpl.Subcriteria>) subcriteriaList.get(criteria);

			return !lista.stream().filter(subcriteria -> {
				String tmpAlias = subcriteria.getAlias() != null ? subcriteria.getAlias() : subcriteria.getPath();
				return tmpAlias != null && tmpAlias.equalsIgnoreCase(alias);
			}).collect(Collectors.toList()).isEmpty();

		} catch (NoSuchFieldException | IllegalAccessException e) {
			WLogger.debug(e);
		}
		return false;
	}

	@Deprecated
	/**
	 * filtra o campo pelo termo informado
	 *
	 * @param campo
	 * @param termo
	 * @return
	 */
	public List<T> autocomplete(String campo, String termo) {
		return autocomplete(campo, termo, null);
	}

	@Deprecated
	public <TT extends BaseEntity> List<TT> autocomplete(Class<TT> clazz, String campo, String termo) {
		return autocomplete(clazz, campo, termo, null);
	}

	/**
	 * filtra o campo pelo termo informado e pelos parametros informados
	 *
	 * @param campo
	 * @param termo
	 * @param parametros
	 * @return
	 */
	@Deprecated
	public List<T> autocomplete(String campo, String termo, QueryParametro... parametros) {
		return autocomplete(null, campo, termo, parametros);
	}

	/**
	 * filtra o campo pelo termo informado e pelos parametros informados
	 *
	 * @param clazz
	 * @param campo
	 * @param termo
	 * @param parametros
	 * @return
	 */
	@Deprecated
	public <TT extends BaseEntity> List<TT> autocomplete(Class<TT> clazz, String campo, String termo,
			QueryParametro... parametros) {
		try {
			this.entityClassAlternativo = clazz;
			Class<TT> cl = clazz != null ? clazz : (Class<TT>) entityClassAlternativo;
			String strQuery = "select e from "
					+ (getEntityClass().getName().substring(cl.getName().lastIndexOf(".") + 1, cl.getName().length()))
					+ " e where upper(str(e." + campo + ")) like :campo ";

			if (parametros != null) {
				for (QueryParametro parametro : parametros) {
					strQuery += " and " + getHqlRestriction(parametro);
				}

			}
			strQuery += " order by e." + campo;

			Query query = entityManager.createQuery(strQuery);
			query.setParameter("campo", termo.toUpperCase() + "%");
			if (parametros != null) {
				setQueryRestrictions(Arrays.asList(parametros), query);
			}

			query.setFirstResult(0);
			query.setMaxResults(10);
			return query.getResultList();
		} catch (Exception e) {
			WLogger.error(e);
			return Collections.emptyList();
		} finally {
			entityClassAlternativo = null;
		}
	}

	@Deprecated
	private void setQueryRestrictions(List<QueryParametro> parametros, Query query) {

		if (parametros != null && query != null) {
			for (QueryParametro parametro : parametros) {
				String atributo = parametro.getAtributo().replaceAll("\\.", "");
				if (parametro.isLikeSearch()) {
					if (parametro.getLikeMatchMode() != null) {
						String valor = parametro.isCaseSensitive() ? parametro.getValor().toString()
								: parametro.getValor().toString().toUpperCase();

						switch (parametro.getLikeMatchMode()) {
						case EXACT:
							query.setParameter(atributo, valor);
							break;
						case START:
							query.setParameter(atributo, "%" + valor);
							break;
						case END:
							query.setParameter(atributo, valor + "%");
							break;
						case ANYWHERE:
							query.setParameter(atributo, "%" + valor + "%");
							break;
						}
					}
				} else {
					if (parametro.getValor() != null) {
						query.setParameter(atributo, parametro.getValor());
					}
				}
			}
		}
	}

	@Deprecated
	private String getHqlRestriction(QueryParametro parametro) {
		String operador = parametro.isNegado() ? "!=" : "=";
		String atributo = "e." + parametro.getAtributo();

		Object valor = parametro.getValor();
		if (valor != null && valor instanceof String) {
			atributo = parametro.isCaseSensitive() ? parametro.getAtributo()
					: "upper(e." + parametro.getAtributo() + ")";
		} else if (valor == null) {
			operador = parametro.isNegado() ? "is not null" : "is null";
		} else {
			if (parametro.isMenor()) {
				operador = "<";
			}
			if (parametro.isMenorIgual()) {
				operador = "<=";
			}
			if (parametro.isMaior()) {
				operador = ">";
			}
			if (parametro.isMaiorIgual()) {
				operador = ">=";
			}
		}

		if (parametro.isLikeSearch()) {
			operador = parametro.isNegado() ? "not like" : "like";

		}
		return String.format("%s %s %s", atributo, operador,
				parametro.getValor() != null ? ":" + parametro.getAtributo().replaceAll("\\.", "") : "");
	}

	/**
	 * Lista todas as entidades pelo atributo/valor
	 *
	 * @param nomeAtributo
	 * @param valorAtributo
	 * @return
	 */
	@Deprecated
	protected List<T> listarPorAtributo(String nomeAtributo, Object valorAtributo) {
		try {
			Criteria criteria = getCriteriaMap(Arrays.asList(QueryParametro.getAtributo(nomeAtributo, valorAtributo)),
					null, null, null);
			return criteria.list();
		} catch (HibernateException e) {
			WLogger.error(e);
			return Collections.emptyList();
		}
	}

	@Deprecated
	private JoinType getJoinType(List<FetchParametro> fetchs, final QueryParametro parametro) {
		if (fetchs == null) {
			return null;
		}
		if (!fetchs.isEmpty()) {
			Optional<FetchParametro> any = fetchs.stream()
					.filter(fetch -> fetch.getAtributo().equals(parametro.getAtributo())).findAny();
			if (any.isPresent()) {
				return any.get().getJoinType();
			} else {
				return null;
			}

		}
		return null;
	}

	@Deprecated
	private Criteria getCriteriaMap(List<QueryParametro> atributos, JoinType joinType, List<FetchParametro> fetchs,
			Integer maxResult) {
		Criteria criteria = getSession().createCriteria(getEntityClass());

		criteria.setCacheable(false);

		if (maxResult != null) {
			criteria.setMaxResults(maxResult);
		}

		Set<String> aliases = new HashSet<>();

		for (QueryParametro parametro : atributos) {
			Object value = parametro.getValor();
			String chaveValor = parametro.getAtributo();
			String key = parametro.getAtributo();
			if (key.contains(".")) {
				String[] alias = key.split("\\.");
				String novaKey = key.substring(0, key.lastIndexOf("."));
				chaveValor = alias[alias.length - 1];

				alias = novaKey.split("\\.");
				String tmpKey = "";
				for (String s : alias) {
					if (tmpKey.isEmpty()) {
						tmpKey = s;
					} else {
						tmpKey += "." + s;
					}
					JoinType jj = null;
					if (joinType == null) {
						jj = getJoinType(fetchs, parametro);
						jj = jj != null ? jj : JoinType.INNER_JOIN;
					}
					if (!aliases.contains(tmpKey)) {
						aliases.add(tmpKey);
						criteria.createAlias(tmpKey, s, joinType != null ? joinType : jj);
					}
				}

				chaveValor = alias[alias.length - 1] + "." + chaveValor;
			}
			if (value != null && !value.toString().isEmpty()) {
				if (value instanceof String) {
					if (parametro.isLikeSearch()) {
						criteria.add(getLikeRestriction(chaveValor, parametro));
					} else {
						Criterion eq = getRestriction(chaveValor, parametro);
						if (!parametro.isCaseSensitive()) {
							((SimpleExpression) eq).ignoreCase();
						}
						if (parametro.isNegado()) {
							criteria.add(Restrictions.not(eq));
						} else {
							criteria.add(eq);
						}
					}
				} else if (value instanceof Collection || value instanceof Arrays) {
					Criterion in = Restrictions.in(chaveValor, (Collection) value);
					if (parametro.isNegado()) {
						criteria.add(Restrictions.not(in));
					} else {
						criteria.add(in);
					}
				} else {
					Criterion eq = getRestriction(chaveValor, parametro);

					if (parametro.isNegado()) {
						criteria.add(Restrictions.not(eq));
					} else {
						criteria.add(eq);
					}
				}
			}
		}
		return criteria;
	}

	@Deprecated
	private Criterion getLikeRestriction(String chaveValor, QueryParametro queryParametro) {
		Criterion like;
		String valor = queryParametro.getValor().toString();
		if (queryParametro.isCaseSensitive()) {
			like = Restrictions.like(chaveValor, valor, queryParametro.getLikeMatchMode());
		} else {
			like = Restrictions.ilike(chaveValor, valor, queryParametro.getLikeMatchMode());
		}
		if (queryParametro.isNegado()) {
			return Restrictions.not(like);
		}
		return like;
	}

	@Deprecated
	private Criterion getRestriction(String chaveValor, QueryParametro queryParametro) {
		if (queryParametro.isMaior()) {
			return Restrictions.gt(chaveValor, queryParametro.getValor());
		}

		if (queryParametro.isMaiorIgual()) {
			return Restrictions.ge(chaveValor, queryParametro.getValor());
		}

		if (queryParametro.isMenor()) {
			return Restrictions.lt(chaveValor, queryParametro.getValor());
		}

		if (queryParametro.isMenorIgual()) {
			return Restrictions.le(chaveValor, queryParametro.getValor());
		}

		return Restrictions.eqOrIsNull(chaveValor, queryParametro.getValor());
	}

	/**
	 * listar todos da entidade
	 *
	 * @return
	 */
	@Deprecated
	public List<T> listar() {
		try {
			return getSession().createCriteria(getEntityClass()).list();
		} catch (HibernateException e) {
			WLogger.error(e);
			return Collections.emptyList();
		}
	}

	@Deprecated
	public List<T> listar(FetchParametro... fetchs) {
		try {
			Criteria criteria = getSession().createCriteria(getEntityClass());
			setFetchs(criteria, fetchs);
			return criteria.list();
		} catch (HibernateException e) {
			WLogger.error(e);
			return Collections.emptyList();
		}
	}

	@Deprecated
	public Page<T> listar(int pagina, int max) {
		return listar(PageRequest.of(pagina, max));
	}

	@Deprecated
	public Page<T> listar(PageRequest paginacao) {
		try {
			Criteria criteria = getSession().createCriteria(getEntityClass());
			criteria.setProjection(Projections.rowCount());

			Long total = (Long) criteria.uniqueResult();
			Page<T> page;
			criteria.setProjection(null);
			if (total > 0) {
				criteria.setMaxResults(paginacao.getPageSize());
				criteria.setFirstResult(paginacao.getPageNumber() * paginacao.getPageSize());
				page = new PageImpl<T>(criteria.list(), paginacao, total);
			} else {
				page = new PageImpl<T>(Collections.emptyList(), paginacao, total);
			}

			return page;
		} catch (HibernateException e) {
			WLogger.error(e);
			return null;
		}
	}

	@Deprecated
	public Page<T> listar(PageRequest paginacao, FetchParametro... fetchs) {
		try {
			Criteria criteria = getSession().createCriteria(getEntityClass());
			setFetchs(criteria, fetchs);
			return getPage(criteria, paginacao, null, null);

		} catch (HibernateException e) {
			WLogger.error(e);
			return null;
		}
	}

	/**
	 * Recupera a entidade pelos atributos no {@link List<QueryParametro>}
	 *
	 * @param atributos
	 * @return
	 */
	@Deprecated
	@Override
	public T recuperarPorAtributo(List<QueryParametro> atributos) {

		try {
			Criteria criteria = getCriteriaMap(atributos, null, Collections.emptyList(), null);

			return (T) criteria.uniqueResult();
		} catch (HibernateException e) {
			WLogger.error(e);
			return null;
		}
	}

	/**
	 * Lista todas as entidades pelo atributo/valor com o {@link JoinType}
	 * especificado
	 *
	 * @param nomeAtributo
	 * @param valorAtributo
	 * @return
	 */
	@Deprecated
	protected List<T> listarPorAtributo(String nomeAtributo, Object valorAtributo, JoinType joinType) {
		try {
			Criteria criteria = getCriteriaMap(Arrays.asList(QueryParametro.getAtributo(nomeAtributo, valorAtributo)),
					joinType, null, null);
			return criteria.list();
		} catch (HibernateException e) {
			WLogger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Lista todas as entidades pelo atributo/valor e efetua o fetch das entidades
	 *
	 * @param nomeAtributo
	 * @param valorAtributo
	 * @param fetchs
	 * @return
	 */
	@Deprecated
	protected List<T> listarPorAtributo(String nomeAtributo, Object valorAtributo, FetchParametro... fetchs) {
		try {
			Criteria criteria = getCriteriaMap(Arrays.asList(QueryParametro.getAtributo(nomeAtributo, valorAtributo)),
					null, Arrays.asList(fetchs), null);
			setFetchs(criteria, fetchs);

			return criteria.list();
		} catch (HibernateException e) {
			WLogger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Lista as entidades filtrando pela {@link List<QueryParametro>} e faz o fetch
	 * das entidades
	 *
	 * @param atributos
	 * @param fetchs
	 * @return
	 */
	@Deprecated
	@Override
	public List<T> listarPorAtributo(List<QueryParametro> atributos, FetchParametro... fetchs) {
		Criteria criteria = getCriteriaMap(atributos, null,
				fetchs != null ? Arrays.asList(fetchs) : Collections.emptyList(), null);
		setFetchs(criteria, fetchs);
		return criteria.list();
	}

	@Override
	@Deprecated
	public Page<T> listarPorAtributo(PageRequest paginacao, List<QueryParametro> atributos, List<Order> orders,
			FetchParametro... fetchs) {
		Criteria criteria = getCriteriaMap(atributos, null,
				fetchs != null ? Arrays.asList(fetchs) : Collections.emptyList(), null);
		setFetchs(criteria, fetchs);
		return getPage(criteria, paginacao, orders, null);
	}
	
	/**
     * Obtem a entidade pelo seu primaryKey e carrega os relacionamentos
     *
     * @param id
     * @param fetchs
     * @return
     */
    @Transactional
    @Deprecated
    public T obterPorId(Serializable id, FetchParametro... fetchs) {
        try {
            String chave = getKey(getEntityClass());

            if (chave != null) {
                return recuperarPorAtributo(chave, id, fetchs);
            }
            return obterPorId(id);
        } catch (Exception e) {
            WLogger.error(e);
            return null;
        }
    }
    
    /**
     * Recupera a entidade pelo atributo/valor e efetua os fetchs das entidades
     *
     * @param nomeAtributo
     * @param valorAtributo
     * @param fetchs
     * @return
     */
    @Deprecated
    protected T recuperarPorAtributo(String nomeAtributo, Object valorAtributo, FetchParametro... fetchs) {
        try {
            Criteria criteria = getCriteriaMap(Arrays.asList(QueryParametro.getAtributo(nomeAtributo, valorAtributo)), null, Arrays.asList(fetchs), null);
            setFetchs(criteria, fetchs);
            return (T) criteria.uniqueResult();
        } catch (Exception e) {
            WLogger.error(e);
            e.printStackTrace();
            return null;
        }
    }
	

}
