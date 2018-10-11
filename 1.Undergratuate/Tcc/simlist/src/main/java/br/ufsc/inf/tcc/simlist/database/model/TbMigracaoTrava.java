package br.ufsc.inf.tcc.simlist.database.model;

import javax.annotation.Generated;

/**
 * TbMigracaoTrava is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class TbMigracaoTrava {

    private Integer id;

    private Boolean locked;

    private String lockedby;

    private java.sql.Timestamp lockgranted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getLockedby() {
        return lockedby;
    }

    public void setLockedby(String lockedby) {
        this.lockedby = lockedby;
    }

    public java.sql.Timestamp getLockgranted() {
        return lockgranted;
    }

    public void setLockgranted(java.sql.Timestamp lockgranted) {
        this.lockgranted = lockgranted;
    }

}

