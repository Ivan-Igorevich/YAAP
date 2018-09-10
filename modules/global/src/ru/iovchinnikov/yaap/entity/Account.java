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
import com.haulmont.cuba.security.entity.User;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s, %s|name,currency")
@Table(name = "YAAP_ACCOUNT")
@Entity(name = "yaap$Account")
public class Account extends StandardEntity {
    private static final long serialVersionUID = -2899950788714353344L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OWNER_ID")
    protected User owner;

    @NotNull
    @Column(name = "IS_DEFAULT", nullable = false)
    protected Boolean isDefault = false;

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

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

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

    @Override
    public String toString() {
        String curr = getCurrency() == null ? "" : getCurrency();
        return String.format("%s, %s", getName(), curr);
    }
}