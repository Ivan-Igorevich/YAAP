package ru.iovchinnikov.yaap.web.account;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.security.global.UserSession;
import ru.iovchinnikov.yaap.entity.Account;
import ru.iovchinnikov.yaap.service.AccountService;

import javax.inject.Inject;
import java.util.Set;

public class AccountEdit extends AbstractEditor<Account> {
    @Inject private UserSession userSession;
    @Inject private AccountService accountService;
    @Inject private DataManager dataManager;

    @Override
    public void commitAndClose() {
        getItem().setOwner(userSession.getUser());
        if (getItem().getIsDefault())
            accountService.setDefault(getItem(), userSession.getUser());
        CommitContext ctx = new CommitContext(getItem());
        Set<Entity> set =  dataManager.commit(ctx);
        close(AbstractEditor.COMMIT_ACTION_ID, true);
    }
}