package ru.iovchinnikov.yaap.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@NamePattern("%s %s|name,id")
@Table(name = "YAAP_TRANSACTION")
@Entity(name = "yaap$Transaction")
public class Transaction extends StandardEntity {
    private static final long serialVersionUID = -1609485236642275921L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @NotNull
    @Column(name = "DIRECTION", nullable = false)
    protected Boolean direction = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category;

    @Column(name = "SOURCE")
    protected String source;

    @NotNull
    @Column(name = "TOTAL", nullable = false)
    protected Double total;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    protected Double amount;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }

    public Boolean getDirection() {
        return direction;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}