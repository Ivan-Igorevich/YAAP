package ru.iovchinnikov.yaap.web.account;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.security.global.UserSession;
import ru.iovchinnikov.yaap.entity.Account;

import javax.inject.Inject;

public class AccountEdit extends AbstractEditor<Account> {
    @Inject private UserSession userSession;


    @Override
    public void commitAndClose() {
        getItem().setOwner(userSession.getUser());
        super.commitAndClose();
    }
}