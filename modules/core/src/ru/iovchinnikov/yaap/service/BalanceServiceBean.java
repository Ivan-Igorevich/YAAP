package ru.iovchinnikov.yaap.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import org.springframework.stereotype.Service;
import ru.iovchinnikov.yaap.entity.Account;
import ru.iovchinnikov.yaap.entity.Period;
import ru.iovchinnikov.yaap.entity.Transaction;

import javax.inject.Inject;

@Service(BalanceService.NAME)
public class BalanceServiceBean implements BalanceService {
    @Inject private DataManager dataManager;

    @Override
    public double getCurrentBalance(Account account) {
        LoadContext<Account> ctx = LoadContext.create(Account.class);
        ctx.setQuery(LoadContext.createQuery("select e from yaap$Account e where e.id = :acctid")
                    .setParameter("acctid", account.getId()))
                .setView("account-view");
        Account acct = dataManager.load(ctx);
        if (acct == null) return 0;
        Period current = acct.getCurrentPeriod();
        if (current == null || current.getTrxs() == null) return 0;
        double balance = current.getBalanceStart();
        for (Transaction t : current.getTrxs()) {
            if (t.getDirection())
                balance += t.getTotal();
            else
                balance -= t.getTotal();
        }
        return balance;
    }
}