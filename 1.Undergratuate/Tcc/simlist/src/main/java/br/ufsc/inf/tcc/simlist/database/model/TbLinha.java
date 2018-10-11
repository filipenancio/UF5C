package br.ufsc.inf.tcc.simlist.database.model;

import javax.annotation.Generated;

/**
 * TbLinha is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class TbLinha {

    private Long coIdLinha;

    private Long coLista;

    private String dsLinha;

    public Long getCoIdLinha() {
        return coIdLinha;
    }

    public void setCoIdLinha(Long coIdLinha) {
        this.coIdLinha = coIdLinha;
    }

    public Long getCoLista() {
        return coLista;
    }

    public void setCoLista(Long coLista) {
        this.coLista = coLista;
    }

    public String getDsLinha() {
        return dsLinha;
    }

    public void setDsLinha(String dsLinha) {
        this.dsLinha = dsLinha;
    }

}

