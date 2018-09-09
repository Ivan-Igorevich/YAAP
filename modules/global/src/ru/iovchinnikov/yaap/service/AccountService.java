package ru.iovchinnikov.yaap.service;


import com.haulmont.cuba.security.entity.User;
import ru.iovchinnikov.yaap.entity.Account;

public interface AccountService {
    String NAME = "yaap_AccountService";

    void setDefault(Account account, User user);

    Account getDefault(User user);
}