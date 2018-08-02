package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.actions.CreateAction;

import javax.inject.Named;
import java.util.Map;

public class TransactionSpend extends AbstractLookup {
    @Named("transactionsTable.create") private CreateAction transactionsTableCreate;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        transactionsTableCreate.setInitialValues(ParamsMap.of("direction", false));
    }
}