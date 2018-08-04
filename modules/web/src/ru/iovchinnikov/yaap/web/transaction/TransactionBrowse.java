package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.GroupTable;
import ru.iovchinnikov.yaap.entity.Transaction;

import javax.inject.Inject;

public class TransactionBrowse extends AbstractLookup {
    @Inject
    private GroupTable<Transaction> transactionsTable;

    @Override
    public void ready() {
        super.ready();
        transactionsTable.setStyleProvider((transaction, s) -> {
            if (transaction.getDirection())
                return "income";
            else
                return "spend";
        });
    }
}