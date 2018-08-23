package ru.iovchinnikov.yaap.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "YAAP_PRODUCT")
@Entity(name = "yaap$Product")
public class Product extends StandardEntity {
    private static final long serialVersionUID = -3396574389192706389L;

    @Column(name = "NAME")
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}