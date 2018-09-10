package ru.iovchinnikov.yaap.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.Date;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Table(name = "YAAP_PERIOD")
@Entity(name = "yaap$Period")
public class Period extends StandardEntity {
    private static final long serialVersionUID = -4190177526490840743L;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "period")
    protected List<Transaction> trxs;

    @NotNull
    @Column(name = "BALANCE_START", nullable = false)
    protected Double balanceStart;

    @Column(name = "BALANCE_END")
    protected Double balanceEnd;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DATE_START", nullable = false)
    protected Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_END")
    protected Date dateEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    protected Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }


    public void setTrxs(List<Transaction> trxs) {
        this.trxs = trxs;
    }

    public List<Transaction> getTrxs() {
        return trxs;
    }

    public void setBalanceStart(Double balanceStart) {
        this.balanceStart = balanceStart;
    }

    public Double getBalanceStart() {
        return balanceStart;
    }

    public void setBalanceEnd(Double balanceEnd) {
        this.balanceEnd = balanceEnd;
    }

    public Double getBalanceEnd() {
        return balanceEnd;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateEnd() {
        return dateEnd;
    }



}