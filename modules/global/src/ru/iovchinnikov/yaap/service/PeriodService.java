package ru.iovchinnikov.yaap.service;


import ru.iovchinnikov.yaap.entity.Account;
import ru.iovchinnikov.yaap.entity.Transaction;

public interface PeriodService {
    String NAME = "yaap_PeriodService";

    void setPeriod(Transaction trx, Account account);
}