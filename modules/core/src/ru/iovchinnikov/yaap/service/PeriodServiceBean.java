package ru.iovchinnikov.yaap.service;

import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;
import ru.iovchinnikov.yaap.entity.Account;
import ru.iovchinnikov.yaap.entity.Period;
import ru.iovchinnikov.yaap.entity.Transaction;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(PeriodService.NAME)
public class PeriodServiceBean implements PeriodService {
    @Inject private Metadata metadata;
    @Inject private DataManager dataManager;

    @Override
    public void setPeriod(Transaction trx, Account account) {
        Period current = account.getCurrentPeriod();
        if (current == null) {
            current = metadata.create(Period.class);
            current.setAccount(account);
            List<Transaction> trxs = new ArrayList<>();
            current.setTrxs(trxs);
            current.setDateStart(new Date(System.currentTimeMillis()));
            current.setBalanceStart(0.0);
            account.setCurrentPeriod(current);
        }
        current.getTrxs().add(trx);
        trx.setPeriod(current);
        CommitContext ctx = new CommitContext(trx, current, account);
        dataManager.commit(ctx);
    }
}