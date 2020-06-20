package br.com.projeto.wanda.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.projeto.wanda.model.enums.TipoRetornoNativeQueryEnum;

/**
 * @author hugnei bosco
 * @version 1.0
 * @since 02/08/2018
 * @see " Map necessário para utilização das consultas nativas
 * 			1 - String - Representa a QUERY sql nativa;
 * 			2 - Integer - Representa o numero maximo de linhas no resultado. pode ser null;
 * 			3 - Lista de QueryParametros - Representa os parametros da consulta;
 * 			4 - Lista String - Lista utilizada para substituir '?' da query nativa pelo
 * 	     	               	atributo utilizado pelo hibernate na lista QueryParametro;
 * 			5 - Class - Representa a classe utilizada para override do repositorioGenerico;
 * 			6 - List<Object[]> {"coluna retorno ', StandardBasicTypes (Representa o tipo de retorno)}, this.retornoConsulta);
 * 			7 - List<Object[]> {int posicao 1,2,3}, QueryParam(atributo, valor) - Utilizado para replace '?' por atributo. onde  o integer faz referencia ao '?';
 * 	        	               PS: Este depois  do replace 'addReplaceParametro'. "
 * */

public class NativeQueryParametro {

    private Map<Integer, Object> mapNativeQuery = new HashMap<>();

    private NativeQueryParametro() {

    }

    public Map<Integer, Object> getMap() {
        return mapNativeQuery;
    }

    public void setMap(Map<Integer, Object> mapNativeQuery) {
        this.mapNativeQuery = mapNativeQuery;
    }

    public static class Builder {

        private String sqlQuery;
        private Integer maxResult = null;
        private Class<?> classeOverride = null ;
        private List<QueryParametro> queryParametros  = new ArrayList<>();
        private List<Object[]> queryParametrosReplace  = new ArrayList<>();
        private List<String> parametrosReplace  = new ArrayList<>();
        private List<Object[]> retornoConsulta  = new ArrayList<>();
        private NativeQueryParametro map = new NativeQueryParametro();
        private List<Object[]> queryParametroList  = new ArrayList<>();


        /**
         * Define a string sql a ser utilizada.
         * @param queryString
         * @return
         * */
        public Builder setSqlQuery(String queryString) {
            if(queryString != null && !queryString.isEmpty()){
                this.sqlQuery = queryString;
            }
            return this;
        }

        /**
         * Define o numero de linhas de retorno da consulta.
         * @param maxResultQuery
         * @return
         * */
        public Builder setMaxResult(Integer maxResultQuery) {
            if(maxResultQuery != null){
                this.maxResult = maxResultQuery;
            }
            return this;
        }

        /**
         * Recebe um int como posicao em referencia ao paramtro da query nativa '?'
         * para ser substituido pelo atributo.
         * @param posicao
         * @param queryParametro
         * @return
         * */
        public Builder addQueryParametro(Integer posicao, QueryParametro queryParametro) {
            if(posicao != null && queryParametro != null && queryParametro.getAtributo()!=null){
                Object[] queryReplace = new Object[]{posicao, queryParametro};
                queryParametrosReplace.add(queryReplace);
            }
            return this;
        }

        /**
         * Define um parametro para consulta.
         * @param queryParametro
         * @return
         * */
        public Builder addQueryParametro(QueryParametro queryParametro) {
            if(queryParametro != null && queryParametro.getAtributo() != null) {
                this.queryParametros.add(queryParametro);
            }
            return this;
        }

        /**
         * Define uma lista de parametro para consulta.
         * @param queryParametroList
         * @return
         * */
        public Builder addQueryParametro(List<QueryParametro> queryParametroList) {
            List<QueryParametro> validos = new ArrayList<>();
            if(queryParametroList != null && !queryParametroList.isEmpty()){
                for (QueryParametro param : queryParametroList) {
                    if(param.getAtributo() != null){
                        this.queryParametros.add(param);
                    }
                }
            }
            return this;
        }

        /**
         * Define uma lista de parametro para consulta.
         * @param queryParametro
         * @return
         * */
        public Builder addQueryParametro(QueryParametro queryParametro, TipoRetornoNativeQueryEnum tipoParamLista) {
            if(queryParametro != null && queryParametro.getAtributo() != null && queryParametro.getValor() != null) {
                Object[] retorno = new Object[]{queryParametro, tipoParamLista};
                this.queryParametroList.add(retorno);
            }
            return this;
        }


        /**
         * Define um replace para o parametro '?' da query nativa.
         * @param parametroReplace
         * @return
         * */
        public Builder addReplaceParametro(String parametroReplace) {
            if(parametroReplace != null && !parametroReplace.isEmpty()){
                this.parametrosReplace.add(parametroReplace);
            }
            return this;
        }

        /**
         * Define uma classe para substituir a do repositorio generico
         * @param classe
         * @return
         * */
        public Builder setClasseOverride(Class<?> classe) {
            if(classe != null){
                this.classeOverride = classe;
            }
            return this;
        }

        /**
         * Define um tipo para cada coluna retornada pela query.
         * @param coluna
         * @param tipoRetorno
         * @return
         * */
        public Builder addRetornoConsulta(String coluna, TipoRetornoNativeQueryEnum tipoRetorno) {
            if(coluna != null && !coluna.isEmpty() && tipoRetorno != null){
                Object[] retorno = new Object[]{coluna, tipoRetorno};
                this.retornoConsulta.add(retorno);
            }
            return this;
        }


        /**
         * Constroi o mapa utilizado pelos metodos de QueryNativa do repositorio generico
         * @return
         * */
        public NativeQueryParametro build() {
            map.getMap().put(1, this.sqlQuery);
            map.getMap().put(2, this.maxResult);
            map.getMap().put(3, this.queryParametros);
            map.getMap().put(4, this.parametrosReplace);
            map.getMap().put(5, this.classeOverride);
            map.getMap().put(6, this.retornoConsulta);
            map.getMap().put(7,this.queryParametrosReplace);
            map.getMap().put(8,this.queryParametroList);
            return map;
        }
    }

}
