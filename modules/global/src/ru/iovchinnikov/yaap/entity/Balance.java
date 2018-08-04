package ru.iovchinnikov.yaap.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "YAAP_BALANCE")
@Entity(name = "yaap$Balance")
public class Balance extends StandardEntity {
    private static final long serialVersionUID = -4190177526490840743L;

    @NotNull
    @Column(name = "CURRENT_", nullable = false)
    protected Double current;

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getCurrent() {
        return current;
    }


}