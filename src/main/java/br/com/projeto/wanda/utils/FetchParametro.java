package br.com.projeto.wanda.utils;

import org.hibernate.FetchMode;
import org.hibernate.sql.JoinType;

/**
 * Monta a estrutura dos parametros para as queries utilizadas no RepositorioGenerico
 */
public class FetchParametro {

	private String atributo;
	private JoinType joinType = JoinType.INNER_JOIN;
	private FetchMode fetchMode = FetchMode.JOIN;

	private FetchParametro() {
	}

	public String getAtributo() {
		return atributo;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public FetchMode getFetchMode() {
		return fetchMode;
	}

	public static class Builder {

		private FetchParametro queryParametro = new FetchParametro();

		/**
		 * Nome do atributo a ser obtivo
		 * @param atributo
		 * @return
		 */
		public Builder setAtributo(String atributo) {
			queryParametro.atributo = atributo;
			return this;
		}

		/**
		 * Tipo de {@link JoinType} a ser usado nas queries
		 * @param valor
		 * @return
		 */
		public Builder setJoinType(JoinType valor) {
			queryParametro.joinType = valor;
			return this;
		}

		/**
		 * Tipo do {@link FetchMode} para este relacionamento
		 * @param fetchMode
		 * @return
		 */
		public Builder setFetchMode(FetchMode fetchMode) {
			queryParametro.fetchMode = fetchMode;
			return this;
		}

		public FetchParametro build() {
			return queryParametro;
		}
	}

	/**
	 * Monta um parametro com o atributo e join
	 * @param atributo
	 * @param joinType
	 * @return
	 */
	public static FetchParametro getFetch(String atributo, JoinType joinType) {
		return new FetchParametro.Builder().
						setAtributo(atributo).
						setJoinType(joinType).build();
	}

	public static FetchParametro getFetch(String atributo, FetchMode fetchMode, JoinType joinType) {
		return new FetchParametro.Builder().
						setAtributo(atributo).
						setJoinType(joinType).
						setFetchMode(fetchMode).build();
	}

	public static FetchParametro getFetch(String atributo) {
		return new FetchParametro.Builder().
						setAtributo(atributo).
						setJoinType(JoinType.INNER_JOIN).build();
	}
}
