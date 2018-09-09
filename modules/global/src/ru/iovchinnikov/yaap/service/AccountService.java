package ru.iovchinnikov.yaap.service;


import com.haulmont.cuba.security.entity.User;
import ru.iovchinnikov.yaap.entity.Account;

import java.util.List;

public interface AccountService {
    String NAME = "yaap_AccountService";

    void setDefault(Account account, User user);
    Account getDefault(User user);
    List<Account> getUserAccounts(User user);
}