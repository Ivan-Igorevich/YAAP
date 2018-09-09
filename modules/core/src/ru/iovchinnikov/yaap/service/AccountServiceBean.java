package ru.iovchinnikov.yaap.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;
import ru.iovchinnikov.yaap.entity.Account;

import javax.inject.Inject;
import java.util.List;

@Service(AccountService.NAME)
public class AccountServiceBean implements AccountService {

    @Inject private DataManager dataManager;

    @Override
    public void setDefault(Account account, User user) {
        List<Account> accts = getAllAccounts(account, user);
        for (Account a : accts) {
            a.setIsDefault(false);
            dataManager.commit(a);
        }
    }

    private List<Account> getAllAccounts(Account account, User user) {
        LoadContext<Account> ctx = LoadContext.create(Account.class);
        ctx.setQuery(LoadContext.createQuery("select e from yaap$Account e where e.owner.id = :userid and e.id <> :acctId")
                .setParameter("userid", user.getId())
                .setParameter("acctId", account.getId()));
        return dataManager.loadList(ctx);
    }
    private List<Account> getAllAccounts(User user) {
        LoadContext<Account> ctx = LoadContext.create(Account.class);
        ctx.setQuery(LoadContext.createQuery("select e from yaap$Account e where e.owner.id = :userid")
                .setParameter("userid", user.getId()));
        return dataManager.loadList(ctx);
    }

    @Override
    public Account getDefault(User user) {
        List<Account> accts = getAllAccounts(user);
        for (Account a : accts) {
            if (a.getIsDefault())
                return a;
        }
        return null;
    }
}