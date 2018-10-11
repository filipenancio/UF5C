package br.ufsc.inf.tcc.simlist.database.model;

import javax.annotation.Generated;

/**
 * TbSimilaridade is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class TbSimilaridade {

    private Long coIdSimilaridade;

    private Long coTecnicaSimilaridade;

    private Long coToken;

    private Long coTokenRef;

    private Integer vlSimilaridade;

    public Long getCoIdSimilaridade() {
        return coIdSimilaridade;
    }

    public void setCoIdSimilaridade(Long coIdSimilaridade) {
        this.coIdSimilaridade = coIdSimilaridade;
    }

    public Long getCoTecnicaSimilaridade() {
        return coTecnicaSimilaridade;
    }

    public void setCoTecnicaSimilaridade(Long coTecnicaSimilaridade) {
        this.coTecnicaSimilaridade = coTecnicaSimilaridade;
    }

    public Long getCoToken() {
        return coToken;
    }

    public void setCoToken(Long coToken) {
        this.coToken = coToken;
    }

    public Long getCoTokenRef() {
        return coTokenRef;
    }

    public void setCoTokenRef(Long coTokenRef) {
        this.coTokenRef = coTokenRef;
    }

    public Integer getVlSimilaridade() {
        return vlSimilaridade;
    }

    public void setVlSimilaridade(Integer vlSimilaridade) {
        this.vlSimilaridade = vlSimilaridade;
    }

}

