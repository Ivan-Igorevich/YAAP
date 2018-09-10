package ru.iovchinnikov.yaap.service;


import ru.iovchinnikov.yaap.entity.Account;

public interface BalanceService {
    String NAME = "yaap_BalanceService";

    double getCurrentBalance(Account account);
}