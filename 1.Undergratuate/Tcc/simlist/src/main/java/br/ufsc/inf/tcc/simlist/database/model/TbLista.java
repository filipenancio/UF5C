package br.ufsc.inf.tcc.simlist.database.model;

import javax.annotation.Generated;

/**
 * TbLista is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class TbLista {

    private Long coIdLista;

    private String dsParagrafo;

    private String dsTitulo;

    private String dsTituloWebpage;

    private Boolean stAnalise;

    public Long getCoIdLista() {
        return coIdLista;
    }

    public void setCoIdLista(Long coIdLista) {
        this.coIdLista = coIdLista;
    }

    public String getDsParagrafo() {
        return dsParagrafo;
    }

    public void setDsParagrafo(String dsParagrafo) {
        this.dsParagrafo = dsParagrafo;
    }

    public String getDsTitulo() {
        return dsTitulo;
    }

    public void setDsTitulo(String dsTitulo) {
        this.dsTitulo = dsTitulo;
    }

    public String getDsTituloWebpage() {
        return dsTituloWebpage;
    }

    public void setDsTituloWebpage(String dsTituloWebpage) {
        this.dsTituloWebpage = dsTituloWebpage;
    }

    public Boolean getStAnalise() {
        return stAnalise;
    }

    public void setStAnalise(Boolean stAnalise) {
        this.stAnalise = stAnalise;
    }

}

