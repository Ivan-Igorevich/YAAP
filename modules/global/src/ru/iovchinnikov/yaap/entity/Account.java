package ru.iovchinnikov.yaap.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.List;
import javax.persistence.OneToMany;

@Table(name = "YAAP_ACCOUNT")
@Entity(name = "yaap$Account")
public class Account extends StandardEntity {
    private static final long serialVersionUID = -2899950788714353344L;

    @Column(name = "NAME")
    protected String name;

    @OneToMany(mappedBy = "account")
    protected List<Period> archive;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRENT_PERIOD_ID")
    protected Period currentPeriod;

    @Column(name = "CURRENCY")
    protected String currency;

    public void setArchive(List<Period> archive) {
        this.archive = archive;
    }

    public List<Period> getArchive() {
        return archive;
    }


    public void setCurrentPeriod(Period currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public Period getCurrentPeriod() {
        return currentPeriod;
    }


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