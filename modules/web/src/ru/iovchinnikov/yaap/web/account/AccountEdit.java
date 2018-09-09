package ru.iovchinnikov.yaap.web.account;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.security.global.UserSession;
import ru.iovchinnikov.yaap.entity.Account;
import ru.iovchinnikov.yaap.service.AccountService;

import javax.inject.Inject;

public class AccountEdit extends AbstractEditor<Account> {
    @Inject private UserSession userSession;
    @Inject private AccountService accountService;

    @Override
    public void commitAndClose() {
        getItem().setOwner(userSession.getUser());
        if (getItem().getIsDefault())
            accountService.setDefault(getItem(), userSession.getUser());
        super.commitAndClose();
    }
}