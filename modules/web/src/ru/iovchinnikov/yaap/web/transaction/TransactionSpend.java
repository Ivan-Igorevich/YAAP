package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.data.GroupDatasource;
import ru.iovchinnikov.yaap.entity.Transaction;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class TransactionSpend extends AbstractLookup {
    @Inject private GroupDatasource<Transaction, UUID> transactionsDs;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
    }

    public void onBtnAddCheckClick() {
        AbstractWindow checkEditor = openWindow("oneCheckEditor", WindowManager.OpenType.DIALOG);
        checkEditor.addCloseListener(actionId -> transactionsDs.refresh());
    }
}