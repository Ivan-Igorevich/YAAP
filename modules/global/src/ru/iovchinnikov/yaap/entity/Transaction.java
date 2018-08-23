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
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamePattern("%s %s|name,id")
@Table(name = "YAAP_TRANSACTION")
@Entity(name = "yaap$Transaction")
public class Transaction extends StandardEntity {
    private static final long serialVersionUID = -1609485236642275921L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NAME_ID")
    protected Product name;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "DATE_", nullable = false)
    protected Date date;

    @NotNull
    @Column(name = "DIRECTION", nullable = false)
    protected Boolean direction = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOURCE_ID")
    protected Company source;

    @NotNull
    @Column(name = "TOTAL", nullable = false)
    protected Double total;

    @Column(name = "VOLUME")
    protected Double volume;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    protected Double amount;

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERIOD_ID")
    protected Period period;

    public Product getName() {
        return name;
    }

    public void setName(Product name) {
        this.name = name;
    }


    public Company getSource() {
        return source;
    }

    public void setSource(Company source) {
        this.source = source;
    }


    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getVolume() {
        return volume;
    }


    public void setPeriod(Period period) {
        this.period = period;
    }

    public Period getPeriod() {
        return period;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }


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