package br.com.projeto.wanda.utils;

import org.hibernate.criterion.MatchMode;

import br.com.projeto.wanda.model.enums.TipoRetornoNativeQueryEnum;

/**
 * Monta a estrutura dos parametros para as queries utilizadas no RepositorioGenerico
 */
public class QueryParametro {

    private String atributo;
    private Object valor;
    private boolean likeSearch;
    private boolean caseSensitive;
    private boolean orOperator;
    private boolean negado;
    private boolean menor;
    private boolean maior;
    private boolean menorIgual;
    private boolean maiorIgual;
    private MatchMode likeMatchMode;
    private TipoRetornoNativeQueryEnum tipoCampoNativo;

    private QueryParametro() {
    }

    public String getAtributo() {
        return atributo;
    }

    public Object getValor() {
        return valor;
    }

    public TipoRetornoNativeQueryEnum getTipoCampoNativo() {
        return tipoCampoNativo;
    }

    public boolean isLikeSearch() {
        return likeSearch;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public boolean isOrOperator() {
        return orOperator;
    }

    public boolean isNegado() {
        return negado;
    }

    public MatchMode getLikeMatchMode() {
        return likeMatchMode;
    }

    public boolean isMenor() {
        return menor;
    }

    public boolean isMaior() {
        return maior;
    }

    public boolean isMenorIgual() {
        return menorIgual;
    }

    public boolean isMaiorIgual() {
        return maiorIgual;
    }

    public static class Builder {

        private QueryParametro queryParametro = new QueryParametro();

        /**
         * Nome do atributo a ser pesquisado
         * Esse nome pode ser composto ex: documento.veiculo.nome
         *
         * @param atributo
         * @return
         */
        public Builder setAtributo(String atributo) {
            queryParametro.atributo = atributo;
            return this;
        }

        /**
         * Valor a ser aplicado no atributo
         *
         * @param valor
         * @return
         */
        public Builder setValor(Object valor) {
            queryParametro.valor = valor;
            return this;
        }

        /**
         * Quando o valor eh String, aplica a pesquisa utilizando o like
         *
         * @return
         */
        public Builder setLikeSearch() {
            queryParametro.likeSearch = true;
            return this;
        }

        /**
         * Quando o likeSearch estah ativo aplica a forma que o like sera feito
         *
         * @param matchMode
         * @return
         */
        public Builder setLikeMatchMode(MatchMode matchMode) {
            queryParametro.likeMatchMode = matchMode;
            return this;
        }

        /**
         * Utiliza o case sensitive nas pesquisa
         * Por padrao eh false
         *
         * @return
         */
        public Builder setCaseSensitive() {
            queryParametro.caseSensitive = true;
            return this;
        }

        /**
         * Utiliza o operador OR para o atributo pesquisado
         * Por padrao eh AND
         *
         * @return
         */
        public Builder setOrOperator() {
            queryParametro.orOperator = true;
            return this;
        }

        /**
         * Nega a operacao
         *
         * @return
         */
        public Builder setNegado() {
            queryParametro.negado = true;
            return this;
        }

        /**
         * o tipo do campo para utilizar como query nativa
         *
         * @param tipoCampoNativo
         * @return
         */
        public Builder setTipoCampoNativo(TipoRetornoNativeQueryEnum tipoCampoNativo) {
            queryParametro.tipoCampoNativo = tipoCampoNativo;
            return this;
        }

        /**
         * Seta o operador como >
         *
         * @return
         */
        public Builder setMaior() {
            queryParametro.maior = true;
            return this;
        }

        /**
         * Seta o operador como <
         *
         * @return
         */
        public Builder setMenor() {
            queryParametro.menor = true;
            return this;
        }

        /**
         * Seta o operador como >=
         *
         * @return
         */
        public Builder setMaiorIgual() {
            queryParametro.maiorIgual = true;
            return this;
        }

        /**
         * Seta o operador como <=
         *
         * @return
         */
        public Builder setMenorIgual() {
            queryParametro.menorIgual = true;
            return this;
        }

        public QueryParametro build() {
            return queryParametro;
        }
    }

    /**
     * Monta um atributo=valor com todos os valores padroes
     *
     * @param atributo
     * @param valorAtributo
     * @return
     */
    public static QueryParametro getAtributo(String atributo, Object valorAtributo) {
        return new QueryParametro.Builder().
                        setAtributo(atributo).
                        setValor(valorAtributo).build();
    }

    public static QueryParametro getAtributo(String atributo, Object valorAtributo, TipoRetornoNativeQueryEnum tipoCampoNativo) {
        return new QueryParametro.Builder().
                        setAtributo(atributo).
                        setValor(valorAtributo).
                        setTipoCampoNativo(tipoCampoNativo).
                        build();
    }
}
