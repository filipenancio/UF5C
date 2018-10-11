package br.ufsc.inf.tcc.simlist.database.model;

import javax.annotation.Generated;

/**
 * TbToken is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class TbToken {

    private Long coIdToken;

    private Long coLinha;

    private Long coLista;

    private Long coTokenPai;

    private String dsToken;

    public Long getCoIdToken() {
        return coIdToken;
    }

    public void setCoIdToken(Long coIdToken) {
        this.coIdToken = coIdToken;
    }

    public Long getCoLinha() {
        return coLinha;
    }

    public void setCoLinha(Long coLinha) {
        this.coLinha = coLinha;
    }

    public Long getCoLista() {
        return coLista;
    }

    public void setCoLista(Long coLista) {
        this.coLista = coLista;
    }

    public Long getCoTokenPai() {
        return coTokenPai;
    }

    public void setCoTokenPai(Long coTokenPai) {
        this.coTokenPai = coTokenPai;
    }

    public String getDsToken() {
        return dsToken;
    }

    public void setDsToken(String dsToken) {
        this.dsToken = dsToken;
    }

}

