package ru.iovchinnikov.yaap.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;

@Table(name = "YAAP_ACCOUNT")
@Entity(name = "yaap$Account")
public class Account extends StandardEntity {
    private static final long serialVersionUID = -2899950788714353344L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "CURRENCY")
    protected String currency;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }


}