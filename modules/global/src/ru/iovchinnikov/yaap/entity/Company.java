package ru.iovchinnikov.yaap.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.validation.constraints.NotNull;

@NamePattern("%s|name")
@Table(name = "YAAP_COMPANY")
@Entity(name = "yaap$Company")
public class Company extends StandardEntity {
    private static final long serialVersionUID = 6852868224083233371L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}